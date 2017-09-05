package com.example.android.sunshine.utilities;

import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.FoodContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.android.sunshine.data.FoodContract.FoodEntry;

public class FakeDataUtils {

    private static int [] weatherIDs = {200,300,500,711,900,962};
    private static String[] testNames = {
            "apples",
            "chicken",
            "eggs",
            "pesto",
            "tomatoes",
            "spinach"};

    /**
     * Creates a single ContentValues object with random weather data for the provided date
     * @param date a normalized date
     * @return ContentValues object filled with random weather data
     */
    private static ContentValues createTestWeatherContentValues(long date, String name) {
        ContentValues testWeatherValues = new ContentValues();
        testWeatherValues.put(FoodContract.FoodEntry.COLUMN_DATE, date);
//        testWeatherValues.put(FoodEntry.COLUMN_NAME, weatherIDs[(int)(Math.random()*10)%5]);
        testWeatherValues.put(FoodEntry.COLUMN_NAME, name);
        return testWeatherValues;
    }

    /**
     * Creates random weather data for 7 days starting today
     * @param context
     */
    public static void insertFakeData(Context context) {
        //Get today's normalized date
        long today = CustomDateUtils.normalizeDate(System.currentTimeMillis());
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        //loop over 7 days starting today onwards
        for(int i=0; i<6; i++) {
            fakeValues.add(
                    FakeDataUtils.createTestWeatherContentValues(
                            today - TimeUnit.DAYS.toMillis(i), testNames[i]));
        }
        // Bulk Insert our new weather data into Sunshine's Database
        context.getContentResolver().bulkInsert(
                FoodEntry.CONTENT_URI,
                fakeValues.toArray(new ContentValues[6]));
    }

    public static String fakeRecipeSearchResults() {
        return "[\n" +
                "  {\n" +
                "    \"id\": 641803,\n" +
                "    \"title\": \"Easy & Delish! ~ Apple Crumble\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Easy---Delish--Apple-Crumble-641803.jpg\",\n" +
                "    \"usedIngredientCount\": 3,\n" +
                "    \"missedIngredientCount\": 4,\n" +
                "    \"likes\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 645152,\n" +
                "    \"title\": \"Grandma's Apple Crisp\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Grandmas-Apple-Crisp-645152.jpg\",\n" +
                "    \"usedIngredientCount\": 3,\n" +
                "    \"missedIngredientCount\": 6,\n" +
                "    \"likes\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 657563,\n" +
                "    \"title\": \"Quick Apple Ginger Pie\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Quick-Apple-Ginger-Pie-657563.jpg\",\n" +
                "    \"usedIngredientCount\": 3,\n" +
                "    \"missedIngredientCount\": 6,\n" +
                "    \"likes\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 639487,\n" +
                "    \"title\": \"Cinnamon Sugar Fried Apples\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Cinnamon-Sugar-Fried-Apples-639487.jpg\",\n" +
                "    \"usedIngredientCount\": 3,\n" +
                "    \"missedIngredientCount\": 8,\n" +
                "    \"likes\": 46\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 643426,\n" +
                "    \"title\": \"Fresh Apple Cake With Caramel Sauce\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Fresh-Apple-Cake-With-Caramel-Sauce-643426.jpg\",\n" +
                "    \"usedIngredientCount\": 3,\n" +
                "    \"missedIngredientCount\": 12,\n" +
                "    \"likes\": 9\n" +
                "  }\n" +
                "]";
    }

    public static String fakeInstructionResults() {
        return "[\n" +
                "  {\n" +
                "    \"name\": \"\",\n" +
                "    \"steps\": [\n" +
                "      {\n" +
                "        \"number\": 1,\n" +
                "        \"step\": \"Preheat the oven to 200 degrees F.\",\n" +
                "        \"ingredients\": [],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404784,\n" +
                "            \"name\": \"oven\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/oven.jpg\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 2,\n" +
                "        \"step\": \"Whisk together the flour, pecans, granulated sugar, light brown sugar, baking powder, baking soda, and salt in a medium bowl.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 19334,\n" +
                "            \"name\": \"light brown sugar\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/brown-sugar-light.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 19335,\n" +
                "            \"name\": \"granulated sugar\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/white-sugar.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 18371,\n" +
                "            \"name\": \"baking powder\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/white-powder.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 18372,\n" +
                "            \"name\": \"baking soda\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/white-powder.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 12142,\n" +
                "            \"name\": \"pecans\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pecans.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 20081,\n" +
                "            \"name\": \"all purpose flour\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/flour.png\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 2047,\n" +
                "            \"name\": \"salt\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404661,\n" +
                "            \"name\": \"whisk\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/whisk.png\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 404783,\n" +
                "            \"name\": \"bowl\",\n" +
                "            \"image\": null\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 3,\n" +
                "        \"step\": \"Whisk together the eggs, buttermilk, butter and vanilla extract and vanilla bean in a small bowl.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 2050,\n" +
                "            \"name\": \"vanilla extract\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/vanilla-extract.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 93622,\n" +
                "            \"name\": \"vanilla bean\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/vanilla.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1230,\n" +
                "            \"name\": \"buttermilk\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/buttermilk.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1001,\n" +
                "            \"name\": \"butter\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/butter.png\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1123,\n" +
                "            \"name\": \"egg\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/egg.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404661,\n" +
                "            \"name\": \"whisk\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/whisk.png\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 404783,\n" +
                "            \"name\": \"bowl\",\n" +
                "            \"image\": null\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 4,\n" +
                "        \"step\": \"Add the egg mixture to the dry mixture and gently mix to combine. Do not overmix.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 1123,\n" +
                "            \"name\": \"egg\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/egg.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": []\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 5,\n" +
                "        \"step\": \"Let the batter sit at room temperature for at least 15 minutes and up to 30 minutes before using.\",\n" +
                "        \"ingredients\": [],\n" +
                "        \"equipment\": []\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 6,\n" +
                "        \"step\": \"Heat a cast iron or nonstick griddle pan over medium heat and brush with melted butter. Once the butter begins to sizzle, use 2 tablespoons of the batter for each pancake and cook until the bubbles appear on the surface and the bottom is golden brown, about 2 minutes, flip over and cook until the bottom is golden brown, 1 to 2 minutes longer.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 1001,\n" +
                "            \"name\": \"butter\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/butter.png\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404779,\n" +
                "            \"name\": \"griddle\",\n" +
                "            \"image\": null\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 404645,\n" +
                "            \"name\": \"frying pan\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/pan.png\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 7,\n" +
                "        \"step\": \"Transfer the pancakes to a platter and keep warm in a 200 degree F oven.\",\n" +
                "        \"ingredients\": [],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404784,\n" +
                "            \"name\": \"oven\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/oven.jpg\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 8,\n" +
                "        \"step\": \"Serve 6 pancakes per person, top each with some of the bourbon butter.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 10014037,\n" +
                "            \"name\": \"bourbon\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/bourbon.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1001,\n" +
                "            \"name\": \"butter\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/butter.png\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": []\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 9,\n" +
                "        \"step\": \"Drizzle with warm maple syrup and dust with confectioners' sugar.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 19336,\n" +
                "            \"name\": \"powdered sugar\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/powdered-sugar.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 19911,\n" +
                "            \"name\": \"maple syrup\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/maple-syrup-or-agave-nectar.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": []\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 10,\n" +
                "        \"step\": \"Garnish with fresh mint sprigs and more toasted pecans, if desired.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 2064,\n" +
                "            \"name\": \"fresh mint\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/mint.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 12142,\n" +
                "            \"name\": \"pecans\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pecans.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": []\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Bourbon Molasses Butter\",\n" +
                "    \"steps\": [\n" +
                "      {\n" +
                "        \"number\": 1,\n" +
                "        \"step\": \"Combine the bourbon and sugar in a small saucepan and cook over high heat until reduced to 3 tablespoons, remove and let cool.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 10014037,\n" +
                "            \"name\": \"bourbon\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/bourbon.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 19335,\n" +
                "            \"name\": \"sugar\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/white-sugar.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404669,\n" +
                "            \"name\": \"sauce pan\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/sauce-pan.jpg\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 2,\n" +
                "        \"step\": \"Put the butter, molasses, salt and cooled bourbon mixture in a food processor and process until smooth.\",\n" +
                "        \"ingredients\": [\n" +
                "          {\n" +
                "            \"id\": 19304,\n" +
                "            \"name\": \"molasses\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/molasses.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 10014037,\n" +
                "            \"name\": \"bourbon\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/bourbon.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1001,\n" +
                "            \"name\": \"butter\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/butter.png\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 2047,\n" +
                "            \"name\": \"salt\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404771,\n" +
                "            \"name\": \"food processor\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/food-processor.png\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 3,\n" +
                "        \"step\": \"Scrape into a bowl, cover with plastic wrap and refrigerate for at least 1 hour to allow the flavors to meld.\",\n" +
                "        \"ingredients\": [],\n" +
                "        \"equipment\": [\n" +
                "          {\n" +
                "            \"id\": 404730,\n" +
                "            \"name\": \"plastic wrap\",\n" +
                "            \"image\": \"https://spoonacular.com/cdn/equipment_100x100/plastic-wrap.jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 404783,\n" +
                "            \"name\": \"bowl\",\n" +
                "            \"image\": null\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"number\": 4,\n" +
                "        \"step\": \"Remove from the refrigerator about 30 minutes before using to soften.\",\n" +
                "        \"ingredients\": [],\n" +
                "        \"equipment\": []\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
    }

    public static String fakeIngredientResults() {
        return "{\n" +
                "  \"vegetarian\": false,\n" +
                "  \"vegan\": false,\n" +
                "  \"glutenFree\": true,\n" +
                "  \"dairyFree\": true,\n" +
                "  \"veryHealthy\": false,\n" +
                "  \"cheap\": false,\n" +
                "  \"veryPopular\": false,\n" +
                "  \"sustainable\": false,\n" +
                "  \"weightWatcherSmartPoints\": 21,\n" +
                "  \"gaps\": \"no\",\n" +
                "  \"lowFodmap\": false,\n" +
                "  \"ketogenic\": false,\n" +
                "  \"whole30\": false,\n" +
                "  \"servings\": 10,\n" +
                "  \"sourceUrl\": \"http://www.epicurious.com/recipes/food/views/Char-Grilled-Beef-Tenderloin-with-Three-Herb-Chimichurri-235342\",\n" +
                "  \"spoonacularSourceUrl\": \"https://spoonacular.com/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992\",\n" +
                "  \"aggregateLikes\": 0,\n" +
                "  \"creditText\": \"Epicurious\",\n" +
                "  \"sourceName\": \"Epicurious\",\n" +
                "  \"extendedIngredients\": [\n" +
                "    {\n" +
                "      \"id\": 1022009,\n" +
                "      \"aisle\": \"Ethnic Foods\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/chili-powder.jpg\",\n" +
                "      \"name\": \"ancho chile powder\",\n" +
                "      \"amount\": 1.5,\n" +
                "      \"unit\": \"teaspoons\",\n" +
                "      \"unitShort\": \"t\",\n" +
                "      \"unitLong\": \"teaspoons\",\n" +
                "      \"originalString\": \"1 1/2 teaspoons chipotle chile powder or ancho chile powder\",\n" +
                "      \"metaInformation\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 13926,\n" +
                "      \"aisle\": \"Meat\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/beef-tenderloin.jpg\",\n" +
                "      \"name\": \"beef tenderloin\",\n" +
                "      \"amount\": 3.5,\n" +
                "      \"unit\": \"pound\",\n" +
                "      \"unitShort\": \"lb\",\n" +
                "      \"unitLong\": \"pounds\",\n" +
                "      \"originalString\": \"1 3 1/2-pound beef tenderloin\",\n" +
                "      \"metaInformation\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1002030,\n" +
                "      \"aisle\": \"Spices and Seasonings\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pepper.jpg\",\n" +
                "      \"name\": \"black pepper\",\n" +
                "      \"amount\": 0.5,\n" +
                "      \"unit\": \"teaspoon\",\n" +
                "      \"unitShort\": \"t\",\n" +
                "      \"unitLong\": \"teaspoons\",\n" +
                "      \"originalString\": \"1/2 teaspoon freshly ground black pepper\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"black\",\n" +
                "        \"freshly ground\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1082047,\n" +
                "      \"aisle\": \"Spices and Seasonings\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\",\n" +
                "      \"name\": \"coarse kosher salt\",\n" +
                "      \"amount\": 1,\n" +
                "      \"unit\": \"tablespoon\",\n" +
                "      \"unitShort\": \"T\",\n" +
                "      \"unitLong\": \"tablespoon\",\n" +
                "      \"originalString\": \"1 tablespoon coarse kosher salt\",\n" +
                "      \"metaInformation\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10019334,\n" +
                "      \"aisle\": \"Baking\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/brown-sugar-dark.jpg\",\n" +
                "      \"name\": \"dark brown sugar\",\n" +
                "      \"amount\": 2,\n" +
                "      \"unit\": \"tablespoons\",\n" +
                "      \"unitShort\": \"T\",\n" +
                "      \"unitLong\": \"tablespoons\",\n" +
                "      \"originalString\": \"2 tablespoons dark brown sugar\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"dark\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11165,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cilantro.png\",\n" +
                "      \"name\": \"fresh cilantro\",\n" +
                "      \"amount\": 2,\n" +
                "      \"unit\": \"cups\",\n" +
                "      \"unitShort\": \"c\",\n" +
                "      \"unitLong\": \"cups\",\n" +
                "      \"originalString\": \"2 cups (packed) stemmed fresh cilantro\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"fresh\",\n" +
                "        \"packed\",\n" +
                "        \"stemmed\",\n" +
                "        \"()\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2064,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/mint.jpg\",\n" +
                "      \"name\": \"fresh mint\",\n" +
                "      \"amount\": 1,\n" +
                "      \"unit\": \"cup\",\n" +
                "      \"unitShort\": \"c\",\n" +
                "      \"unitLong\": \"cup\",\n" +
                "      \"originalString\": \"1 cup (packed) stemmed fresh mint\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"fresh\",\n" +
                "        \"packed\",\n" +
                "        \"stemmed\",\n" +
                "        \"()\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11297,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/parsley.jpg\",\n" +
                "      \"name\": \"fresh parsley\",\n" +
                "      \"amount\": 3,\n" +
                "      \"unit\": \"cups\",\n" +
                "      \"unitShort\": \"c\",\n" +
                "      \"unitLong\": \"cups\",\n" +
                "      \"originalString\": \"3 cups (packed) stemmed fresh parsley\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"fresh\",\n" +
                "        \"packed\",\n" +
                "        \"stemmed\",\n" +
                "        \"()\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11215,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/garlic.jpg\",\n" +
                "      \"name\": \"garlic cloves\",\n" +
                "      \"amount\": 3,\n" +
                "      \"unit\": \"\",\n" +
                "      \"unitShort\": \"\",\n" +
                "      \"unitLong\": \"\",\n" +
                "      \"originalString\": \"3 garlic cloves, peeled\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"peeled\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1002030,\n" +
                "      \"aisle\": \"Spices and Seasonings\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pepper.jpg\",\n" +
                "      \"name\": \"ground pepper\",\n" +
                "      \"amount\": 1,\n" +
                "      \"unit\": \"teaspoon\",\n" +
                "      \"unitShort\": \"t\",\n" +
                "      \"unitLong\": \"teaspoon\",\n" +
                "      \"originalString\": \"1 teaspoon ground black pepper\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"black\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9152,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/lemon-juice.jpg\",\n" +
                "      \"name\": \"lemon juice\",\n" +
                "      \"amount\": 3,\n" +
                "      \"unit\": \"tablespoons\",\n" +
                "      \"unitShort\": \"T\",\n" +
                "      \"unitLong\": \"tablespoons\",\n" +
                "      \"originalString\": \"3 tablespoons fresh lemon juice\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"fresh\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4053,\n" +
                "      \"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/olive-oil.jpg\",\n" +
                "      \"name\": \"olive oil\",\n" +
                "      \"amount\": 0.75,\n" +
                "      \"unit\": \"cup\",\n" +
                "      \"unitShort\": \"c\",\n" +
                "      \"unitLong\": \"cups\",\n" +
                "      \"originalString\": \"3/4 cup olive oil\",\n" +
                "      \"metaInformation\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4053,\n" +
                "      \"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/olive-oil.jpg\",\n" +
                "      \"name\": \"olive oil\",\n" +
                "      \"amount\": 2,\n" +
                "      \"unit\": \"tablespoons\",\n" +
                "      \"unitShort\": \"T\",\n" +
                "      \"unitLong\": \"tablespoons\",\n" +
                "      \"originalString\": \"2 tablespoons olive oil\",\n" +
                "      \"metaInformation\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11821,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/red-bell-pepper.png\",\n" +
                "      \"name\": \"red pepper\",\n" +
                "      \"amount\": 0.5,\n" +
                "      \"unit\": \"teaspoon\",\n" +
                "      \"unitShort\": \"t\",\n" +
                "      \"unitLong\": \"teaspoons\",\n" +
                "      \"originalString\": \"1/2 teaspoon dried crushed red pepper\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"dried\",\n" +
                "        \"red\",\n" +
                "        \"crushed\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1022068,\n" +
                "      \"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/red-wine-vinegar.jpg\",\n" +
                "      \"name\": \"red wine vinegar\",\n" +
                "      \"amount\": 3,\n" +
                "      \"unit\": \"tablespoons\",\n" +
                "      \"unitShort\": \"T\",\n" +
                "      \"unitLong\": \"tablespoons\",\n" +
                "      \"originalString\": \"3 tablespoons Sherry wine vinegar or red wine vinegar\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"red\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1012047,\n" +
                "      \"aisle\": \"Spices and Seasonings\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\",\n" +
                "      \"name\": \"sea salt\",\n" +
                "      \"amount\": 1,\n" +
                "      \"unit\": \"teaspoon\",\n" +
                "      \"unitShort\": \"t\",\n" +
                "      \"unitLong\": \"teaspoon\",\n" +
                "      \"originalString\": \"1 teaspoon fine sea salt\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"fine\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11677,\n" +
                "      \"aisle\": \"Produce\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/shallots.jpg\",\n" +
                "      \"name\": \"shallots\",\n" +
                "      \"amount\": 2,\n" +
                "      \"unit\": \"\",\n" +
                "      \"unitShort\": \"\",\n" +
                "      \"unitLong\": \"\",\n" +
                "      \"originalString\": \"2 medium shallots, peeled, quartered\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"medium\",\n" +
                "        \"peeled\",\n" +
                "        \"quartered\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1002028,\n" +
                "      \"aisle\": \"Spices and Seasonings\",\n" +
                "      \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/paprika.jpg\",\n" +
                "      \"name\": \"sweet paprika\",\n" +
                "      \"amount\": 1,\n" +
                "      \"unit\": \"tablespoon\",\n" +
                "      \"unitShort\": \"T\",\n" +
                "      \"unitLong\": \"tablespoon\",\n" +
                "      \"originalString\": \"1 tablespoon sweet smoked paprika*\",\n" +
                "      \"metaInformation\": [\n" +
                "        \"smoked\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": 156992,\n" +
                "  \"title\": \"Char-Grilled Beef Tenderloin with Three-Herb Chimichurri\",\n" +
                "  \"readyInMinutes\": 45,\n" +
                "  \"image\": \"https://spoonacular.com/recipeImages/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992.jpg\",\n" +
                "  \"imageType\": \"jpg\",\n" +
                "  \"instructions\": \"PreparationFor spice rub:                                        Combine all ingredients in small bowl.                                                                            Do ahead: Can be made 2 days ahead. Store airtight at room temperature.                                    For chimichurri sauce:                                        Combine first 8 ingredients in blender; blend until almost smooth. Add 1/4 of parsley, 1/4 of cilantro, and 1/4 of mint; blend until incorporated. Add remaining herbs in 3 more additions, pureeing until almost smooth after each addition.                                                                            Do ahead: Can be made 3 hours ahead. Cover; chill.                                    For beef tenderloin:                                        Let beef stand at room temperature 1 hour.                                                                            Prepare barbecue (high heat). Pat beef dry with paper towels; brush with oil. Sprinkle all over with spice rub, using all of mixture (coating will be thick). Place beef on grill; sear 2 minutes on each side. Reduce heat to medium-high. Grill uncovered until instant-read thermometer inserted into thickest part of beef registers 130F for medium-rare, moving beef to cooler part of grill as needed to prevent burning, and turning occasionally, about 40 minutes. Transfer to platter; cover loosely with foil and let rest 15 minutes. Thinly slice beef crosswise. Serve with chimichurri sauce.                                                                            *Available at specialty foods stores and from tienda.com.\"\n" +
                "}";
    }
}
