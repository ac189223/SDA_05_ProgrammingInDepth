package exercise04;

import com.google.gson.*;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokeOperator02 {
    private static final PokeOperator02 PO = new PokeOperator02();
    private static final String FILE_POKE = "src/main/java/exercise04/poke.json";

    public static void main(String[] args) throws Exception {
        while (true) {
            int chosenOption = -1;
            Object[] options = {4, 3, 2, 1};
            while (chosenOption < 0 || chosenOption > 3)
                chosenOption = JOptionPane.showOptionDialog(new JFrame("      =-_-="),
                        "Please choose what to do" +
                                "\n1: Search pokemon by name" +
                                "\n2: Search pokemons' type" +
                                "\n3: Search location by name" +
                                "\n4: Exit the program",
                        "      =-_-=",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            switch (chosenOption) {
                case 0:
                    System.exit(0);
                case 1:
                    PO.locationCheck();
                    break;
                case 2:
                case 3:
                    PO.pokeSearch(chosenOption);
                    break;
            }
        }
    }

    public void pokeSearch(int chosenOption) throws Exception {
        Object[] pokeNames = Stream.of("","bulbasaur","ivysaur","venusaur","charmander","charmeleon",
                "charizard","squirtle","wartortle","blastoise","caterpie","metapod",
                "butterfree","weedle","kakuna","beedrill","pidgey","pidgeotto",
                "pidgeot","rattata","raticate","spearow","fearow","ekans",
                "arbok","pikachu","raichu","sandshrew","sandslash","nidoran-f",
                "nidorina","nidoqueen","nidoran-m","nidorino","nidoking","clefairy",
                "clefable","vulpix","ninetales","jigglypuff","wigglytuff","zubat",
                "golbat","oddish","gloom","vileplume","paras","parasect",
                "venonat","venomoth","diglett","dugtrio","meowth","persian",
                "psyduck","golduck","mankey","primeape","growlithe","arcanine",
                "poliwag","poliwhirl","poliwrath","abra","kadabra","alakazam",
                "machop","machoke","machamp","bellsprout","weepinbell","victreebel",
                "tentacool","tentacruel","geodude","graveler","golem","ponyta",
                "rapidash","slowpoke","slowbro","magnemite","magneton","farfetchd",
                "doduo","dodrio","seel","dewgong","grimer","muk",
                "shellder","cloyster","gastly","haunter","gengar","onix",
                "drowzee","hypno","krabby","kingler","voltorb","electrode",
                "exeggcute","exeggutor","cubone","marowak","hitmonlee","hitmonchan",
                "lickitung","koffing","weezing","rhyhorn","rhydon","chansey",
                "tangela","kangaskhan","horsea","seadra","goldeen","seaking",
                "staryu","starmie","mr-mime","scyther","jynx","electabuzz",
                "magmar","pinsir","tauros","magikarp","gyarados","lapras",
                "ditto","eevee","vaporeon","jolteon","flareon","porygon",
                "omanyte","omastar","kabuto","kabutops","aerodactyl","snorlax",
                "articuno","zapdos","moltres","dratini","dragonair","dragonite",
                "mewtwo","mew","chikorita","bayleef","meganium","cyndaquil",
                "quilava","typhlosion","totodile","croconaw","feraligatr","sentret",
                "furret","hoothoot","noctowl","ledyba","ledian","spinarak",
                "ariados","crobat","chinchou","lanturn","pichu","cleffa",
                "igglybuff","togepi","togetic","natu","xatu","mareep",
                "flaaffy","ampharos","bellossom","marill","azumarill","sudowoodo",
                "politoed","hoppip","skiploom","jumpluff","aipom","sunkern",
                "sunflora","yanma","wooper","quagsire","espeon","umbreon",
                "murkrow","slowking","misdreavus","unown","wobbuffet","girafarig",
                "pineco","forretress","dunsparce","gligar","steelix","snubbull",
                "granbull","qwilfish","scizor","shuckle","heracross","sneasel",
                "teddiursa","ursaring","slugma","magcargo","swinub","piloswine",
                "corsola","remoraid","octillery","delibird","mantine","skarmory",
                "houndour","houndoom","kingdra","phanpy","donphan","porygon2",
                "stantler","smeargle","tyrogue","hitmontop","smoochum","elekid",
                "magby","miltank","blissey","raikou","entei","suicune",
                "larvitar","pupitar","tyranitar","lugia","ho-oh","celebi",
                "treecko","grovyle","sceptile","torchic","combusken","blaziken",
                "mudkip","marshtomp","swampert","poochyena","mightyena","zigzagoon",
                "linoone","wurmple","silcoon","beautifly","cascoon","dustox",
                "lotad","lombre","ludicolo","seedot","nuzleaf","shiftry",
                "taillow","swellow","wingull","pelipper","ralts","kirlia",
                "gardevoir","surskit","masquerain","shroomish","breloom","slakoth",
                "vigoroth","slaking","nincada","ninjask","shedinja","whismur",
                "loudred","exploud","makuhita","hariyama","azurill","nosepass",
                "skitty","delcatty","sableye","mawile","aron","lairon",
                "aggron","meditite","medicham","electrike","manectric","plusle",
                "minun","volbeat","illumise","roselia","gulpin","swalot",
                "carvanha","sharpedo","wailmer","wailord","numel","camerupt",
                "torkoal","spoink","grumpig","spinda","trapinch","vibrava",
                "flygon","cacnea","cacturne","swablu","altaria","zangoose",
                "seviper","lunatone","solrock","barboach","whiscash","corphish",
                "crawdaunt","baltoy","claydol","lileep","cradily","anorith",
                "armaldo","feebas","milotic","castform","kecleon","shuppet",
                "banette","duskull","dusclops","tropius","chimecho","absol",
                "wynaut","snorunt","glalie","spheal","sealeo","walrein",
                "clamperl","huntail","gorebyss","relicanth","luvdisc","bagon",
                "shelgon","salamence","beldum","metang","metagross","regirock",
                "regice","registeel","latias","latios","kyogre","groudon",
                "rayquaza","jirachi","deoxys-normal","turtwig","grotle","torterra",
                "chimchar","monferno","infernape","piplup","prinplup","empoleon",
                "starly","staravia","staraptor","bidoof","bibarel","kricketot",
                "kricketune","shinx","luxio","luxray","budew","roserade",
                "cranidos","rampardos","shieldon","bastiodon","burmy","wormadam-plant",
                "mothim","combee","vespiquen","pachirisu","buizel","floatzel",
                "cherubi","cherrim","shellos","gastrodon","ambipom","drifloon",
                "drifblim","buneary","lopunny","mismagius","honchkrow","glameow",
                "purugly","chingling","stunky","skuntank","bronzor","bronzong",
                "bonsly","mime-jr","happiny","chatot","spiritomb","gible",
                "gabite","garchomp","munchlax","riolu","lucario","hippopotas",
                "hippowdon","skorupi","drapion","croagunk","toxicroak","carnivine",
                "finneon","lumineon","mantyke","snover","abomasnow","weavile",
                "magnezone","lickilicky","rhyperior","tangrowth","electivire","magmortar",
                "togekiss","yanmega","leafeon","glaceon","gliscor","mamoswine",
                "porygon-z","gallade","probopass","dusknoir","froslass","rotom",
                "uxie","mesprit","azelf","dialga","palkia","heatran",
                "regigigas","giratina-altered","cresselia","phione","manaphy","darkrai",
                "shaymin-land","arceus","victini","snivy","servine","serperior",
                "tepig","pignite","emboar","oshawott","dewott","samurott",
                "patrat","watchog","lillipup","herdier","stoutland","purrloin",
                "liepard","pansage","simisage","pansear","simisear","panpour",
                "simipour","munna","musharna","pidove","tranquill","unfezant",
                "blitzle","zebstrika","roggenrola","boldore","gigalith","woobat",
                "swoobat","drilbur","excadrill","audino","timburr","gurdurr",
                "conkeldurr","tympole","palpitoad","seismitoad","throh","sawk",
                "sewaddle","swadloon","leavanny","venipede","whirlipede","scolipede",
                "cottonee","whimsicott","petilil","lilligant","basculin-red-striped","sandile",
                "krokorok","krookodile","darumaka","darmanitan-standard","maractus","dwebble",
                "crustle","scraggy","scrafty","sigilyph","yamask","cofagrigus",
                "tirtouga","carracosta","archen","archeops","trubbish","garbodor",
                "zorua","zoroark","minccino","cinccino","gothita","gothorita",
                "gothitelle","solosis","duosion","reuniclus","ducklett","swanna",
                "vanillite","vanillish","vanilluxe","deerling","sawsbuck","emolga",
                "karrablast","escavalier","foongus","amoonguss","frillish","jellicent",
                "alomomola","joltik","galvantula","ferroseed","ferrothorn","klink",
                "klang","klinklang","tynamo","eelektrik","eelektross","elgyem",
                "beheeyem","litwick","lampent","chandelure","axew","fraxure",
                "haxorus","cubchoo","beartic","cryogonal","shelmet","accelgor",
                "stunfisk","mienfoo","mienshao","druddigon","golett","golurk",
                "pawniard","bisharp","bouffalant","rufflet","braviary","vullaby",
                "mandibuzz","heatmor","durant","deino","zweilous","hydreigon",
                "larvesta","volcarona","cobalion","terrakion","virizion","tornadus-incarnate",
                "thundurus-incarnate","reshiram","zekrom","landorus-incarnate","kyurem","keldeo-ordinary",
                "meloetta-aria","genesect","chespin","quilladin","chesnaught","fennekin",
                "braixen","delphox","froakie","frogadier","greninja","bunnelby",
                "diggersby","fletchling","fletchinder","talonflame","scatterbug","spewpa",
                "vivillon","litleo","pyroar","flabebe","floette","florges",
                "skiddo","gogoat","pancham","pangoro","furfrou","espurr",
                "meowstic-male","honedge","doublade","aegislash-shield","spritzee","aromatisse",
                "swirlix","slurpuff","inkay","malamar","binacle","barbaracle",
                "skrelp","dragalge","clauncher","clawitzer","helioptile","heliolisk",
                "tyrunt","tyrantrum","amaura","aurorus","sylveon","hawlucha",
                "dedenne","carbink","goomy","sliggoo","goodra","klefki",
                "phantump","trevenant","pumpkaboo-average","gourgeist-average","bergmite","avalugg",
                "noibat","noivern","xerneas","yveltal","zygarde","diancie",
                "hoopa","volcanion","rowlet","dartrix","decidueye","litten",
                "torracat","incineroar","popplio","brionne","primarina","pikipek",
                "trumbeak","toucannon","yungoos","gumshoos","grubbin","charjabug",
                "vikavolt","crabrawler","crabominable","oricorio-baile","cutiefly","ribombee",
                "rockruff","lycanroc-midday","wishiwashi-solo","mareanie","toxapex","mudbray",
                "mudsdale","dewpider","araquanid","fomantis","lurantis","morelull",
                "shiinotic","salandit","salazzle","stufful","bewear","bounsweet",
                "steenee","tsareena","comfey","oranguru","passimian","wimpod",
                "golisopod","sandygast","palossand","pyukumuku","type-null","silvally",
                "minior-red-meteor","komala","turtonator","togedemaru","mimikyu-disguised","bruxish",
                "drampa","dhelmise","jangmo-o","hakamo-o","kommo-o","tapu-koko",
                "tapu-lele","tapu-bulu","tapu-fini","cosmog","cosmoem","solgaleo",
                "lunala","nihilego","buzzwole","pheromosa","xurkitree","celesteela",
                "kartana","guzzlord","necrozma","magearna","marshadow","poipole",
                "naganadel","stakataka","blacephalon","zeraora").collect(Collectors.toList()).stream().sorted().toArray();
        JPanel panel = new JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter pokemon name or choose from the list");
        panel.add(label);
        JComboBox jcb = new JComboBox(pokeNames);
        jcb.setEditable(true);
        panel.add(jcb);

        ImageIcon pokeApiIcon = new ImageIcon("src/main/java/exercise04/pok/pokeApi.png", "pokeApi");

        String pokeName = "";
        do {
            JOptionPane.showMessageDialog(null, panel, "      =-_-=", JOptionPane.PLAIN_MESSAGE, pokeApiIcon);
            pokeName = (String) jcb.getSelectedItem();
        } while (pokeName == null || pokeName.equals("") || !Arrays.asList(pokeNames).contains(pokeName));

        makeJsonPrettyToFile(makeHTTPRequest("pokemon/" + pokeName));

        ImageIcon pokeIcon;
        if (Files.exists(Paths.get("src/main/java/exercise04/pok/" + pokeName + ".png")))
            pokeIcon = new ImageIcon("src/main/java/exercise04/pok/" + pokeName + ".png", pokeName);
        else
            pokeIcon = pokeApiIcon;
        if (chosenOption == 2)
            JOptionPane.showMessageDialog(new JFrame("      =-_-="),filterPrintoutPokemonType(),
                    "      =-_-=", JOptionPane.PLAIN_MESSAGE, pokeIcon);
        else if (chosenOption == 3)
            JOptionPane.showMessageDialog(new JFrame("      =-_-="),filterPrintoutPokemon(),
                    "      =-_-=", JOptionPane.PLAIN_MESSAGE, pokeIcon);
    }

    public void makeJsonPrettyToFile(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(uglyJson);
        try (FileWriter writer = new FileWriter(FILE_POKE)) {
            gson.toJson(jsonElement, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String makeHTTPRequest(String string) {
        String urlString = "https://pokeapi.co/api/v2/" + string + "/";
        String response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Scanner responseScanner = new Scanner(connection.getInputStream());
            while (responseScanner.hasNext()) {
                response = responseScanner.nextLine();
            }
        } catch (IOException e) {
        }
        return response;
    }

    private String filterPrintoutPokemon() throws Exception {
        JsonObject jsonObject = (JsonObject) readJson(FILE_POKE);
        return "Id " + jsonObject.get("id") + " - name " + jsonObject.get("name") + ", height " +
                jsonObject.get("height") + ", weight " + jsonObject.get("weight");
    }

    private String filterPrintoutPokemonType() throws Exception {
        JsonObject jsonObject = (JsonObject) readJson(FILE_POKE);
        StringBuilder printout = new StringBuilder();
        printout.append("Type: ");
        JsonArray types = jsonObject.get("types").getAsJsonArray();
        ArrayList<String> typesList = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            if (i > 0)
                printout.append(", ");
            printout.append(types.get(i).getAsJsonObject().get("type").getAsJsonObject().get("name"));
            typesList.add(types.get(i).getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString());
        }
        PokeTypeDataSet dataSet = new PokeTypeDataSet();
        for (String t:typesList) {
            makeJsonPrettyToFile(makeHTTPRequest("type/" + t));
            jsonObject = (JsonObject) readJson(FILE_POKE);
            JsonArray proAttacks = jsonObject.get("damage_relations").getAsJsonObject().get("double_damage_to").getAsJsonArray();
            for (int i = 0; i < proAttacks.size(); i++)
                if (!dataSet.getProAttack().contains(proAttacks.get(i).getAsJsonObject().get("name").getAsString()))
                    dataSet.getProAttack().add(proAttacks.get(i).getAsJsonObject().get("name").getAsString());
            JsonArray poorAttacks = jsonObject.get("damage_relations").getAsJsonObject().get("no_damage_to").getAsJsonArray();
            for (int i = 0; i < poorAttacks.size(); i++)
                if (!dataSet.getPoorAttack().contains(poorAttacks.get(i).getAsJsonObject().get("name").getAsString()))
                    dataSet.getPoorAttack().add(poorAttacks.get(i).getAsJsonObject().get("name").getAsString());
            JsonArray proDefence = jsonObject.get("damage_relations").getAsJsonObject().get("no_damage_from").getAsJsonArray();
            for (int i = 0; i < proDefence.size(); i++)
                if (!dataSet.getProDefence().contains(proDefence.get(i).getAsJsonObject().get("name").getAsString()))
                    dataSet.getProDefence().add(proDefence.get(i).getAsJsonObject().get("name").getAsString());
            JsonArray poorDefence = jsonObject.get("damage_relations").getAsJsonObject().get("double_damage_from").getAsJsonArray();
            for (int i = 0; i < poorDefence.size(); i++)
                if (!dataSet.getPoorDefence().contains(poorDefence.get(i).getAsJsonObject().get("name").getAsString()))
                    dataSet.getPoorDefence().add(poorDefence.get(i).getAsJsonObject().get("name").getAsString());
        }
        printout.append("\npro attack against: ");
        dataSet.getProAttack().forEach(t -> printout.append(t).append(", "));
        printout.substring(0, printout.length()-2);
        printout.append("\npoor attack against: ");
        dataSet.getPoorAttack().forEach(t -> printout.append(t).append(", "));
        printout.substring(0, printout.length()-2);
        printout.append("\npro defence against: ");
        dataSet.getProDefence().forEach(t -> printout.append(t).append(", "));
        printout.substring(0, printout.length()-2);
        printout.append("\npoor defence against: ");
        dataSet.getPoorDefence().forEach(t -> printout.append(t).append(", "));
        printout.substring(0, printout.length()-2);
        return printout.toString();
    }

    public Object readJson(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(reader);
    }

    private void locationCheck() throws Exception {
        Object[] locNames = Stream.of("","canalave-city","eterna-city","pastoria-city","sunyshore-city","sinnoh-pokemon-league",
                "oreburgh-mine","valley-windworks","eterna-forest","fuego-ironworks","mt-coronet","great-marsh",
                "solaceon-ruins","sinnoh-victory-road","ravaged-path","oreburgh-gate","stark-mountain","spring-path",
                "turnback-cave","snowpoint-temple","wayward-cave","ruin-maniac-cave","trophy-garden",
                "iron-island","old-chateau","lake-verity","lake-valor","lake-acuity","valor-lakefront",
                "acuity-lakefront","sinnoh-route-201","sinnoh-route-202","sinnoh-route-203","sinnoh-route-204","sinnoh-route-205",
                "sinnoh-route-206","sinnoh-route-207","sinnoh-route-208","sinnoh-route-209","lost-tower","sinnoh-route-210",
                "sinnoh-route-211","sinnoh-route-212","sinnoh-route-213","sinnoh-route-214","sinnoh-route-215","sinnoh-route-216",
                "sinnoh-route-217","sinnoh-route-218","sinnoh-route-219","sinnoh-route-221","sinnoh-route-222","sinnoh-route-224",
                "sinnoh-route-225","sinnoh-route-227","sinnoh-route-228","sinnoh-route-229","twinleaf-town","celestic-town",
                "resort-area","sinnoh-sea-route-220","sinnoh-sea-route-223","sinnoh-sea-route-226","sinnoh-sea-route-230","blackthorn-city",
                "burned-tower","celadon-city","cerulean-city","cherrygrove-city","cianwood-city","cinnabar-island",
                "dark-cave","digletts-cave","dragons-den","ecruteak-city","fuchsia-city","ice-path",
                "ilex-forest","lake-of-rage","mt-moon","mt-mortar","mt-silver","national-park",
                "new-bark-town","olivine-city","pallet-town","rock-tunnel","kanto-route-1","kanto-route-10",
                "kanto-route-11","kanto-route-12","kanto-route-13","kanto-route-14","kanto-route-15","kanto-route-16",
                "kanto-route-17","kanto-route-18","kanto-sea-route-19","kanto-route-2","kanto-sea-route-20","kanto-sea-route-21",
                "kanto-route-22","kanto-route-24","kanto-route-25","kanto-route-26","kanto-route-27","kanto-route-28",
                "johto-route-29","kanto-route-3","johto-route-30","johto-route-31","johto-route-32","johto-route-33",
                "johto-route-34","johto-route-35","johto-route-36","johto-route-37","johto-route-38","johto-route-39",
                "kanto-route-4","johto-sea-route-40","johto-sea-route-41","johto-route-42","johto-route-43","johto-route-44",
                "johto-route-45","johto-route-46","johto-route-47","johto-route-48","kanto-route-5","kanto-route-6",
                "kanto-route-7","kanto-route-8","kanto-route-9","ruins-of-alph","seafoam-islands","slowpoke-well",
                "sprout-tower","bell-tower","tohjo-falls","union-cave",
                "cerulean-cave","unknown-all-poliwag","unknown-all-rattata",
                "unknown-all-bugs","vermilion-city","kanto-victory-road-1","violet-city","viridian-city","viridian-forest",
                "whirl-islands","kanto-route-23","power-plant","kanto-victory-road-2","pokemon-tower","pokemon-mansion",
                "kanto-safari-zone","sandgem-town","floaroma-town","solaceon-town","jubilife-city",
                "oreburgh-city","hearthome-city","veilstone-city","snowpoint-city","spear-pillar","pal-park",
                "amity-square","floaroma-meadow","fullmoon-island","sendoff-spring","flower-paradise",
                "maniac-tunnel","galactic-hq","verity-lakefront","newmoon-island","sinnoh-battle-tower","fight-area",
                "survival-area","seabreak-path","sinnoh-hall-of-origin-1","sinnoh-hall-of-origin-2","verity-cavern","valor-cavern",
                "acuity-cavern","jubilife-tv","poketch-co","gts","trainers-school","mining-museum",
                "sinnoh-flower-shop","sinnoh-cycle-shop","contest-hall","poffin-house","sinnoh-foreign-building","pokemon-day-care",
                "veilstone-store","sinnoh-game-corner","canalave-library","vista-lighthouse","sunyshore-market","footstep-house",
                "sinnoh-cafe","grand-lake","sinnoh-restaurant","battle-park","battle-frontier","battle-factory",
                "battle-castle","battle-arcade","battle-hall","distortion-world","sinnoh-global-terminal","sinnoh-villa",
                "battleground","rotoms-room","tg-eterna-bldg","iron-ruins","iceberg-ruins","rock-peak-ruins",
                "azalea-town","goldenrod-city","mahogany-town","pewter-city","lavender-town","indigo-plateau",
                "saffron-city","johto-lighthouse","team-rocket-hq","goldenrod-tunnel","mt-silver-cave",
                "pokeathlon-dome","ss-aqua","safari-zone-gate","cliff-cave","frontier-access",
                "bellchime-trail","sinjoh-ruins","embedded-tower","pokewalker","cliff-edge-gate",
                "radio-tower","day-care-couple","link-trade-arrive","link-trade-met","kanto","johto",
                "hoenn","sinnoh","distant-land","traveling-man","riley","cynthia",
                "mystery-zone","lovely-place","pokemon-ranger","faraway-place","pokemon-movie","pokemon-movie-06",
                "pokemon-movie-07","pokemon-movie-08","pokemon-movie-09","pokemon-movie-10","pokemon-movie-11","pokemon-movie-12",
                "pokemon-movie-13","pokemon-movie-14","pokemon-movie-15","pokemon-movie-16","pokemon-cartoon","space-world",
                "space-world-06","space-world-07","space-world-08","space-world-09","space-world-10","space-world-11",
                "space-world-12","space-world-13","space-world-14","space-world-15","space-world-16","pokemon-festa",
                "pokemon-festa-06","pokemon-festa-07","pokemon-festa-08","pokemon-festa-09","pokemon-festa-10","pokemon-festa-11",
                "pokemon-festa-12","pokemon-festa-13","pokemon-festa-14","pokemon-festa-15","pokemon-festa-16","pokepark",
                "pokepark-06","pokepark-07","pokepark-08","pokepark-09","pokepark-10","pokepark-11",
                "pokepark-12","pokepark-13","pokepark-14","pokepark-15","pokepark-16","pokemon-center",
                "pc-tokyo","pc-osaka","pc-fukuoka","pc-nagoya","pc-sapporo","pc-yokohama",
                "nintendo-world","pokemon-event","pokemon-event-06","pokemon-event-07","pokemon-event-08","pokemon-event-09",
                "pokemon-event-10","pokemon-event-11","pokemon-event-12","pokemon-event-13","pokemon-event-14","pokemon-event-15",
                "pokemon-event-16","wi-fi-event","wi-fi-gift","pokemon-fan-club","event-site","concert-event",
                "mr-pokemon","primo","unova-mystery-zone","unova-faraway-place","nuvema-town","accumula-town",
                "striaton-city","nacrene-city","castelia-city","nimbasa-city","driftveil-city","mistralton-city",
                "icirrus-city","opelucid-city","unova-route-1","unova-route-2","unova-route-3","unova-route-4",
                "unova-route-5","unova-route-6","unova-route-7","unova-route-8","unova-route-9","unova-route-10",
                "unova-route-11","unova-route-12","unova-route-13","unova-route-14","unova-route-15","unova-route-16",
                "unova-route-17","unova-route-18","dreamyard","pinwheel-forest","desert-resort","relic-castle",
                "cold-storage","chargestone-cave","twist-mountain","dragonspiral-tower","unova-victory-road","lacunosa-town",
                "undella-town","anville-town","unova-pokemon-league","ns-castle","royal-unova","gear-station",
                "battle-subway","musical-theater","black-city","white-forest","unity-tower","wellspring-cave",
                "mistralton-cave","rumination-field","celestial-tower","moor-of-icirrus","unova-shopping-mall","challengers-cave",
                "poke-transfer-lab","giant-chasm","liberty-garden","p2-laboratory","skyarrow-bridge","driftveil-drawbridge",
                "tubeline-bridge","village-bridge","marvelous-bridge","entralink","abundant-shrine","undella-bay",
                "lostlorn-forest","trial-chamber","guidance-chamber","entree-forest","accumula-gate","undella-gate",
                "nacrene-gate","castelia-gate","nimbasa-gate","opelucid-gate","black-gate","white-gate",
                "bridge-gate","route-gate","abyssal-ruins","petalburg-city","slateport-city","lilycove-city",
                "mossdeep-city","sootopolis-city","ever-grande-city","meteor-falls","rusturf-tunnel","granite-cave",
                "petalburg-woods","jagged-pass","fiery-path","mt-pyre","seafloor-cavern","cave-of-origin",
                "hoenn-victory-road","shoal-cave","new-mauville","abandoned-ship","sky-pillar","hoenn-route-101",
                "hoenn-route-102","hoenn-route-103","hoenn-route-104","hoenn-route-105","hoenn-route-106","hoenn-route-107",
                "hoenn-route-108","hoenn-route-109","hoenn-route-110","hoenn-route-111","hoenn-route-112","hoenn-route-113",
                "hoenn-route-114","hoenn-route-115","hoenn-route-116","hoenn-route-117","hoenn-route-118","hoenn-route-119",
                "hoenn-route-120","hoenn-route-121","hoenn-route-122","hoenn-route-123","hoenn-route-124","hoenn-route-125",
                "hoenn-route-126","hoenn-route-127","hoenn-route-128","hoenn-route-129","hoenn-route-130","hoenn-route-131",
                "hoenn-route-132","hoenn-route-133","hoenn-route-134","hoenn-safari-zone","dewford-town","pacifidlog-town",
                "magma-hideout","mirage-tower","desert-underpass","artisan-cave","hoenn-altering-cave","monean-chamber",
                "liptoo-chamber","weepth-chamber","dilford-chamber","scufib-chamber","rixy-chamber","viapos-chamber",
                "ss-anne","mt-ember","berry-forest","icefall-cave","pattern-bush",
                "lost-cave","kindle-road","treasure-beach","cape-brink","bond-bridge","three-isle-port",
                "resort-gorgeous","water-labyrinth","five-isle-meadow","memorial-pillar","outcast-island","green-path",
                "water-path","ruin-valley","trainer-tower","canyon-entrance","sevault-canyon","tanoby-ruins",
                "one-island","four-island",
                "five-island","kanto-altering-cave","aspertia-city","virbank-city","humilau-city",
                "pokestar-studios","join-avenue","floccesy-town","lentimas-town","unova-route-19","unova-route-20",
                "unova-route-21","unova-route-22","unova-route-23","castelia-sewers","floccesy-ranch","virbank-complex",
                "reversal-mountain","strange-house","unova-victory-road-2","plasma-frigate","relic-passage","clay-tunnel",
                "white-treehollow","black-tower","seaside-cave","cave-of-being","hidden-grotto","marine-tube",
                "virbank-gate","aspertia-gate","nature-sanctuary","medal-secretariat","underground-ruins","rocky-mountain-room",
                "glacier-room","iron-room","pledge-grove","littleroot-town","oldale-town","lavaridge-town",
                "fallarbor-town","verdanturf-town","mauville-city","rustboro-city","fortree-city","underwater",
                "mt-chimney","mirage-island","southern-island","sealed-chamber","scorched-slab","island-cave",
                "desert-ruins","ancient-tomb","inside-of-truck","secret-base","hoenn-battle-tower","vaniville-town",
                "kalos-route-1","vaniville-pathway","aquacorde-town","kalos-route-2","avance-trail","santalune-forest",
                "kalos-route-3","ouvert-way","santalune-city","kalos-route-4","parterre-way","lumiose-city",
                "prism-tower","lysandre-labs","kalos-route-5","versant-road","camphrier-town","shabboneau-castle",
                "kalos-route-6","palais-lane","parfum-palace","kalos-route-7","rivière-walk","cyllage-city",
                "kalos-route-8","muraille-coast","ambrette-town","kalos-route-9","spikes-passage","battle-chateau",
                "kalos-route-10","menhir-trail","geosenge-town","kalos-route-11","miroir-way","reflection-cave",
                "shalour-city","tower-of-mastery","kalos-route-12","fourrage-road","coumarine-city","kalos-route-13",
                "lumiose-badlands","kalos-route-14","laverre-nature-trail","laverre-city","poke-ball-factory","kalos-route-15",
                "brun-way","dendemille-town","kalos-route-16","melancolie-path","frost-cavern","kalos-route-17",
                "mamoswine-road","anistar-city","kalos-route-18","vallee-etroite-way","couriway-town","kalos-route-19",
                "grande-vallee-way","snowbelle-city","kalos-route-20","winding-woods","pokemon-village","kalos-route-21",
                "dernière-way","kalos-route-22","detourner-way","kalos-victory-road","kalos-pokemon-league","kiloude-city",
                "battle-maison","azure-bay","dendemille-gate","couriway-gate","ambrette-gate","lumiose-gate",
                "shalour-gate","coumarine-gate","laverre-gate","anistar-gate","snowbelle-gate","glittering-cave",
                "connecting-cave","zubat-roost","kalos-power-plant","team-flare-secret-hq","terminus-cave","lost-hotel",
                "chamber-of-emptiness","sea-spirits-den","friend-safari","blazing-chamber","flood-chamber","ironworks-chamber",
                "dragonmark-chamber","radiant-chamber","pokemon-league-gate","lumiose-station","kiloude-station","ambrette-aquarium",
                "unknown-dungeon","hoenn-pokemon-league","team-aqua-hideout","sea-mauville","team-magma-hideout","battle-resort",
                "ss-tidal","mirage-forest","mirage-cave","mirage-mountain","trackless-forest","pathless-plain",
                "nameless-cavern","fabled-cave","gnarled-den","crescent-isle","secret-islet","soaring-in-the-sky",
                "secret-shore","secret-meadow","alola-route-1--hauoli-outskirts","alola-route-1","alola-route-3","alola-route-2",
                "kalae-bay","melemele-sea","hauoli-city--beachfront","hauoli-city--shopping-district","hauoli-city--marina","iki-town",
                "mahalo-trail","mahalo-trail--plank-bridge","ruins-of-conflict","ten-carat-hill","ten-carat-hill--farthest-hollow","hauoli-cemetery",
                "melemele-meadow","seaward-cave","berry-fields","verdant-cavern--trial-site","verdant-cavern--totems-den","alola-route-4",
                "alola-route-5","alola-route-6","alola-route-7","alola-route-8","alola-route-9","hano-grand-resort",
                "hano-beach","akala-meadow","paniola-town","heahea-city","konikoni-city","royal-avenue",
                "memorial-hill","paniola-ranch","wela-volcano-park","wela-volcano-park--totems-den","brooklet-hill","brooklet-hill--totems-den",
                "lush-jungle","ruins-of-life","akala-outskirts","digletts-tunnel","battle-royal-dome","alola-route-10",
                "alola-route-11","secluded-shore","alola-route-13","tapu-village","alola-route-15","alola-route-16",
                "alola-route-17","alola-route-12","haina-desert","alola-route-14","ulaula-meadow","po-town",
                "malie-city","malie-garden","mount-hokulani","blush-mountain","ruins-of-abundance","lake-of-the-sunne",
                "lake-of-the-moone","mount-lanakila","shady-house","thrifty-megamart--abandoned-site","hokulani-observatory","alola-pokemon-league",
                "poni-meadow","poni-wilds","ancient-poni-path","poni-breaker-coast","poni-grove","poni-plains",
                "poni-coast","poni-gauntlet","seafolk-village","vast-poni-canyon","altar-of-the-sunne","altar-of-the-moone",
                "ruins-of-hope","resolution-cave","exeggutor-island","battle-tree","aether-paradise","ultra-space",
                "malie-city--outer-cape").collect(Collectors.toList()).stream().sorted().toArray();

        JPanel panel = new JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter location name or choose from the list");
        panel.add(label);
        JComboBox jcb = new JComboBox(locNames);
        jcb.setEditable(true);
        panel.add(jcb);

        ImageIcon pokeApiIcon = new ImageIcon("src/main/java/exercise04/pok/pokeApi.png", "pokeApi");

        String locName = "";
        do {
            JOptionPane.showMessageDialog(null, panel, "      =-_-=", JOptionPane.PLAIN_MESSAGE, pokeApiIcon);
            locName = (String) jcb.getSelectedItem();
        } while (locName == null || locName.equals("") || !Arrays.asList(locNames).contains(locName));

        makeJsonPrettyToFile(makeHTTPRequest("location/" + locName));
        JOptionPane.showMessageDialog(new JFrame("      =-_-="),filterPrintoutLocation(),
                "      =-_-=", JOptionPane.PLAIN_MESSAGE, pokeApiIcon);
    }

    private String filterPrintoutLocation() throws Exception {
        JsonObject jsonObject = (JsonObject) readJson(FILE_POKE);
        StringBuilder printout = new StringBuilder();
        printout.append("Id ").append(jsonObject.get("id")).append(" - name ").append(jsonObject.get("name")).append("(");
        JsonArray names = jsonObject.get("names").getAsJsonArray();
        for (int i = 0; i < names.size(); i++) {
            if (i > 0)
                printout.append(", ");
            printout.append(names.get(i).getAsJsonObject().get("name"));
        }
        printout.append("), region ").append(jsonObject.get("region").getAsJsonObject().get("name"));
        return printout.toString();
    }

    private class PokeTypeDataSet {
        private ArrayList<String> proAttack;
        private ArrayList<String> poorAttack;
        private ArrayList<String> proDefence;
        private ArrayList<String> poorDefence;

        public PokeTypeDataSet() {
            this.proAttack = new ArrayList<>();
            this.poorAttack = new ArrayList<>();
            this.proDefence = new ArrayList<>();
            this.poorDefence = new ArrayList<>();
        }

        public ArrayList<String> getProAttack() { return proAttack; }
        public ArrayList<String> getPoorAttack() { return poorAttack; }
        public ArrayList<String> getProDefence() { return proDefence; }
        public ArrayList<String> getPoorDefence() { return poorDefence; }
    }
}
