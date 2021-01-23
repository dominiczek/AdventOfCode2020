package dominiczek.aoc2020.day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Product {

    List<String> ingredients;
    List<String> allergens;

    public Product(String raw) {
        String ingredientsRaw = raw.split("\\(")[0];
        String[] ingredients = ingredientsRaw.split("\\s");

        this.ingredients = Arrays.asList(ingredients);

        String allergensRaw = raw.split("contains\\s")[1];

        allergensRaw = allergensRaw.substring(0, allergensRaw.length()-1);

        String[] allergens = allergensRaw.split(",\\s");

        this.allergens = Arrays.asList(allergens);

    }

}

public class Solution {


    public static Map<String, List<Set<String>>> allergensVsProducts = new HashMap<>();
    public static Set<String> allAllergens = new HashSet<>();
    public static Set<String> allIngredients = new HashSet<>();
    public static List<Product> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        long result = 0;

        List<String> rawData = Files.readAllLines(Paths.get("input21.txt"));
        for(String data:rawData) {
            Product product = new Product(data);
            allAllergens.addAll(product.allergens);
            allIngredients.addAll(product.ingredients);
            products.add(product);
        }

        for(String allergen:allAllergens) {
            allergensVsProducts.put(allergen, new ArrayList<>());
        }

        Set<String> remainingAllergens = new HashSet<>(allAllergens);
        Set<String> remainingIngredients = new HashSet<>(allIngredients);

        for(Product product:products) {
            for (String allergen:product.allergens) {
                allergensVsProducts.get(allergen).add(new HashSet<>(product.ingredients));
            }
        }
        for(String allergen: allAllergens) {
            List<Set<String>> ingByProduct = allergensVsProducts.get(allergen);

            Set<String> finalSet = new HashSet<>();
            for(Set<String> ingredients:ingByProduct) {
                if(finalSet.isEmpty()) {
                    finalSet.addAll(ingredients);
                } else {
                    finalSet.retainAll(ingredients);
                }
            }
            System.out.println(allergen+": " + finalSet);
            remainingIngredients.removeAll(finalSet);
        }

        for (String ing:remainingIngredients) {
            for(Product p:products) {
                if(p.ingredients.contains(ing)) {
                    result++;
                }
            }
        }

        System.out.println(result);

    }

}
