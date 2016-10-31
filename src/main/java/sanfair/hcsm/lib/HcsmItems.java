package sanfair.hcsm.lib;

public class HcsmItems {

    public static enum items {
        LUGBOXTRAP("lugboxTrap", "ItemLugboxTrap"),
        FISHNET("fishnet", "ItemFishnet");

        private String unlocolazidedName;
        private String registryName;

        items(String unlocolazidedName, String registryName) {
            this.unlocolazidedName = unlocolazidedName;
            this.registryName = registryName;            
        }

        public String getRegistryName() {
            return registryName;
        }

        public String getUnlocolazidedName() {
            return unlocolazidedName;
        }
    }

}
