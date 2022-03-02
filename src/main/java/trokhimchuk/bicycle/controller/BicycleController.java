package trokhimchuk.bicycle.controller;

import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.exeptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bicycle")
public class BicycleController {
    private int counter = 4;

    public List<Map<String, String>> bicycles = new ArrayList<>(){{
        add(new HashMap<String, String>() {{put("id", "1"); put("text", "one bike"); put("firm", "nike"); }});
        add(new HashMap<String, String>() {{put("id", "2"); put("text", "two bike"); }});
        add(new HashMap<String, String>() {{put("id", "3"); put("text", "three bike"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return bicycles;
    }

    @GetMapping("{id}")
    public Map<String, String> getBicycle(@PathVariable String id) {
        return getBic(id);
    }

    private Map<String, String> getBic(@PathVariable String id){
        return bicycles.stream()
                .filter(bicycle -> bicycle.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }


    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> bicycle){
        bicycle.put("id", String.valueOf(counter++));
        bicycles.add(bicycle);

        return bicycle;
    }
    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> bicycle){
        Map<String, String> bicycleFromDB = getBic(id);

        bicycleFromDB.putAll(bicycle);
        bicycleFromDB.put("id", id);
        return bicycleFromDB;
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> bic = getBic(id);

        bicycles.remove(bic);
    }

}
