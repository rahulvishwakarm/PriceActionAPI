package com.priceactiontrader.priceaction.controller;

import com.priceactiontrader.priceaction.model.PriceAction;
import java.lang.reflect.Field;
import com.priceactiontrader.priceaction.model.PriceActionM1;
import com.priceactiontrader.priceaction.service.PriceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.function.Function;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/priceaction")
@CrossOrigin
public class PriceActionTraderController {
    @Autowired
    private PriceActionService priceActionService;

    // get all priceaction from end point
    // http://localhost:{{port}}/api/v1/priceaction
    @GetMapping("/getAll")
    public List<PriceActionM1> getAllPriceActions(
            @RequestParam Map<String, String> filters,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<PriceActionM1> allPriceActions = priceActionService.getAllPriceAction();

        List<PriceActionM1> filteredPriceActions = allPriceActions.stream()
                .filter(priceAction -> matchesFilters(priceAction, filters))
                .collect(Collectors.toList());
        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, filteredPriceActions.size());
        return filteredPriceActions.subList(startIndex, endIndex);
    }

    private boolean matchesFilters(PriceActionM1 priceAction, Map<String, String> filters) {
        Map<String, Function<PriceActionM1, String>> filterMappings = new HashMap<>();
        filterMappings.put("id", action -> String.valueOf(action.getId()));
        filterMappings.put("name", PriceActionM1::getName);
        filterMappings.put("author", PriceActionM1::getAuthor);
        filterMappings.put("title", PriceActionM1::getTitle);
        filterMappings.put("description", PriceActionM1::getDescription);
        filterMappings.put("url", PriceActionM1::getUrl);
        filterMappings.put("urlToImage", PriceActionM1::getUrlToImage);
        filterMappings.put("publishedAt", PriceActionM1::getPublishedAt);
        filterMappings.put("content", PriceActionM1::getContent);

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            if (!isValidFilterField(field)) {
                continue;
            }
            Function<PriceActionM1, String> getter = filterMappings.get(field);
            if (getter != null && !getter.apply(priceAction).equals(value)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidFilterField(String field) {
        return Arrays.asList("id", "name", "author", "title", "description", "url", "urlToImage", "publishedAt", "content")
                .contains(field);
    }

    // add new priceaction to get point
    // http://localhost:{{port}}/api/v1/priceaction/add
    @PostMapping("/add")
    public String add(@RequestBody PriceActionM1 priceaction) {
        // Check if description length exceeds the allowed limit (255 characters)
        int maxDescriptionLength = 255;
        String originalDescription = priceaction.getDescription();

        if (originalDescription.length() > maxDescriptionLength) {
            // Trim or truncate the description to fit within the limit
            String truncatedDescription = originalDescription.substring(0, maxDescriptionLength);
            priceaction.setDescription(truncatedDescription);
        }
        // Save the modified priceaction
        priceActionService.savePriceAction(priceaction);
        return "PriceAction Added Successfully";
    }


    // add new priceactions to get points
    // http://localhost:{{port}}/api/v1/priceaction/add-multiple
    @PostMapping("/add-multiple")
    public String addMultiple(@RequestBody List<PriceActionM1> priceActions) {
        int maxStringLength = 255;

        for (PriceActionM1 priceAction : priceActions) {
            Field[] fields = priceAction.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.getType().equals(String.class)) {
                    try {
                        field.setAccessible(true);
                        String value = (String) field.get(priceAction);
                        if (value != null && value.length() > maxStringLength) {
                            field.set(priceAction, value.substring(0, maxStringLength));
                        }
                    } catch (IllegalAccessException e) {
                        // Handle the exception if needed
                    }
                }
            }
        }
        priceActionService.savePriceActions(priceActions);
        return "PriceActions Added Successfully";
    }


    // delete all priceaction at end point
    // http://localhost:{{port}}/
    @DeleteMapping("/")
    public String deleteAll() {
        priceActionService.deletePriceAction();
        return "All PriceAction Deleted Succesfully";
    }

    // delete specific priceaction by id
    // http://localhost:{{port}}/api/v1/priceaction/{id}
    @DeleteMapping("/{id}")
    public String deleteSpecific(@PathVariable("id") PriceActionM1 priceaction) {
        priceActionService.deleteSpecificPriceAction(priceaction.getId());
        return "PriceAction "+priceaction.getId()+" is deleted Succesfully!!";
    }

    // http://localhost:{{port}}/api/v1/priceaction/{id}
    @PutMapping("/{id}")
    public String updatePriceAction(@RequestBody PriceActionM1 priceaction, @PathVariable("id") PriceActionM1 priceactions) {
        priceActionService.saveOrUpdate(priceaction,priceactions.getId());
        return "PriceAction "+priceactions.getId()+" is Updated Succesfully!!";
    }

}
