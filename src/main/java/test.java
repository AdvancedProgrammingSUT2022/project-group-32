import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import enums.UnitType;

class templateUnit {
    public String Name;
    public int Cost;
    public String Combat;
    public int Strength;
    public int Ranged;
    public int Range;
    public int Movement;
    public String Resources;
    public String Technology;
}

public class test {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"Archer\":\n" +
                "  {\"Name\":\"Archer\",\"Cost\":70,\"Combat\":\"Archery\",\"Strength\":4,\"Ranged\":6,\"Range\":2,\"Movement\":2,\"Resources\":null,\"Technology\":\"Archery\"},\n" +
                "  \"Chariot Archer\":\n" +
                "  {\"Name\":\"Chariot Archer\",\"Cost\":60,\"Combat\":\"Mounted\",\"Strength\":3,\"Ranged\":6,\"Range\":2,\"Movement\":4,\"Resources\":\"Horses\",\"Technology\":\"The Wheel\"},\n" +
                "  \"Scout\":\n" +
                "  {\"Name\":\"Scout\",\"Cost\":25,\"Combat\":\"Recon\",\"Strength\":4,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":null},\n" +
                "  \"Settler\":\n" +
                "  {\"Name\":\"Settler\",\"Cost\":89,\"Combat\":\"Civilian\",\"Strength\":0,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":null},\n" +
                "  \"Spearman\":\n" +
                "  {\"Name\":\"Spearman\",\"Cost\":50,\"Combat\":\"Melee\",\"Strength\":7,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":\"Bronze Working\"},\n" +
                "  \"Warrior\":\n" +
                "  {\"Name\":\"Warrior\",\"Cost\":40,\"Combat\":\"Melee\",\"Strength\":6,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":null},\n" +
                "  \"Worker\":\n" +
                "  {\"Name\":\"Worker\",\"Cost\":70,\"Combat\":\"Civilian\",\"Strength\":0,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":null},\n" +
                "  \"Catapult\":\n" +
                "  {\"Name\":\"Catapult\",\"Cost\":100,\"Combat\":\"Siege\",\"Strength\":4,\"Ranged\":14,\"Range\":2,\"Movement\":2,\"Resources\":\"Iron\",\"Technology\":\"Mathematics\"},\n" +
                "  \"Horseman\":\n" +
                "  {\"Name\":\"Horseman\",\"Cost\":80,\"Combat\":\"Mounted\",\"Strength\":12,\"Ranged\":0,\"Range\":0,\"Movement\":4,\"Resources\":\"Horses\",\"Technology\":\"Horseback Riding\"},\n" +
                "  \"Swordsman\":\n" +
                "  {\"Name\":\"Swordsman\",\"Cost\":80,\"Combat\":\"Melee\",\"Strength\":11,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":\"Iron\",\"Technology\":\"Iron Working\"},\n" +
                "  \"Crossbowman\":\n" +
                "  {\"Name\":\"Crossbowman\",\"Cost\":120,\"Combat\":\"Archery\",\"Strength\":6,\"Ranged\":12,\"Range\":2,\"Movement\":2,\"Resources\":null,\"Technology\":\"Machinery\"},\n" +
                "  \"Knight\":\n" +
                "  {\"Name\":\"Knight\",\"Cost\":150,\"Combat\":\"Mounted\",\"Strength\":18,\"Ranged\":0,\"Range\":0,\"Movement\":3,\"Resources\":\"Horses\",\"Technology\":\"Chivalry\"},\n" +
                "  \"Longswordsman\":\n" +
                "  {\"Name\":\"Longswordsman\",\"Cost\":150,\"Combat\":\"Melee\",\"Strength\":18,\"Ranged\":0,\"Range\":0,\"Movement\":3,\"Resources\":\"Iron\",\"Technology\":\"Steel\"},\n" +
                "  \"Pikeman\":\n" +
                "  {\"Name\":\"Pikeman\",\"Cost\":100,\"Combat\":\"Melee\",\"Strength\":10,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":\"Civil Service\"},\n" +
                "  \"Trebuchet\":\n" +
                "  {\"Name\":\"Trebuchet\",\"Cost\":170,\"Combat\":\"Siege\",\"Strength\":6,\"Ranged\":20,\"Range\":2,\"Movement\":2,\"Resources\":\"Iron\",\"Technology\":\"Physics\"},\n" +
                "  \"Canon\":\n" +
                "  {\"Name\":\"Canon\",\"Cost\":250,\"Combat\":\"Siege\",\"Strength\":10,\"Ranged\":26,\"Range\":2,\"Movement\":2,\"Resources\":null,\"Technology\":\"Chemistry\"},\n" +
                "  \"Cavalry\":\n" +
                "  {\"Name\":\"Cavalry\",\"Cost\":260,\"Combat\":\"Mounted\",\"Strength\":25,\"Ranged\":0,\"Range\":0,\"Movement\":3,\"Resources\":\"Horses\",\"Technology\":\"Military Science\"},\n" +
                "  \"Lancer\":\n" +
                "  {\"Name\":\"Lancer\",\"Cost\":220,\"Combat\":\"Mounted\",\"Strength\":22,\"Ranged\":0,\"Range\":0,\"Movement\":4,\"Resources\":\"Horses\",\"Technology\":\"Metallurgy\"},\n" +
                "  \"Musketman\":\n" +
                "  {\"Name\":\"Musketman\",\"Cost\":120,\"Combat\":\"Gunpowder\",\"Strength\":16,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":\"Gunpowder\"},\n" +
                "  \"Rifleman\":\n" +
                "  {\"Name\":\"Rifleman\",\"Cost\":200,\"Combat\":\"Gunpowder\",\"Strength\":25,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":\"Rifling\"},\n" +
                "  \"Anti-Tank Gun\":\n" +
                "  {\"Name\":\"Anti-Tank Gun\",\"Cost\":300,\"Combat\":\"Gunpowder\",\"Strength\":32,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":\"Replaceable Parts\"},\n" +
                "  \"Artillery\":\n" +
                "  {\"Name\":\"Artillery\",\"Cost\":420,\"Combat\":\"Siege\",\"Strength\":16,\"Ranged\":32,\"Range\":3,\"Movement\":2,\"Resources\":null,\"Technology\":\"Dynamite\"},\n" +
                "  \"Infantry\":\n" +
                "  {\"Name\":\"Infantry\",\"Cost\":300,\"Combat\":\"Gunpowder\",\"Strength\":36,\"Ranged\":0,\"Range\":0,\"Movement\":2,\"Resources\":null,\"Technology\":\"Replaceable Parts\"},\n" +
                "  \"Panzer\":\n" +
                "  {\"Name\":\"Panzer\",\"Cost\":450,\"Combat\":\"Armored\",\"Strength\":60,\"Ranged\":0,\"Range\":0,\"Movement\":5,\"Resources\":null,\"Technology\":\"Combustion\"},\n" +
                "  \"Tank\":\n" +
                "  {\"Name\":\"Tank\",\"Cost\":450,\"Combat\":\"Armored\",\"Strength\":50,\"Ranged\":0,\"Range\":0,\"Movement\":4,\"Resources\":null,\"Technology\":\"Combustion\"}\n" +
                "}";

        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject(UnitType.ARTILLERY.getName());
        Gson gson = new Gson();
        String jj = jobject.toString();
        templateUnit unit = gson.fromJson(jj, templateUnit.class);
        System.out.println(unit.Cost);
//        System.out.println(jobject.toString());
//        String result = jobject.get("translatedText").getAsString();

    }
}
