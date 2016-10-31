package sanfair.hcsm.lib;

public class HcsmBlocks {
    public static enum blocks {
        ANIMALTRAP("animaltrap","BlockAnimalTrap"),
        FISHTRAP("fishtrap","fishtrap");
        private String unlocolazidedName;
        private String registryName;

        blocks(String unlocolazidedName, String registryName) {
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
