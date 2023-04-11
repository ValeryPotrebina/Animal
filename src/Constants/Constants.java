package Constants;

public class Constants {


    public static class GameWindowConstants {
        public final static int FPS_SET = 120;
        public final static int UPS_SET = 200;
        public final static int SIZE_DEFAULT = 32;
        public final static int SIZE_WIDTH = 26;
        public final static int SIZE_HEIGHT = 14;
        public final static int TILE_SIZE_DEFAULT = 32;
        public final static int GAME_WIDTH_DEFAULT = SIZE_DEFAULT * SIZE_WIDTH;
        public final static int GAME_HEIGHT_DEFAULT = SIZE_DEFAULT * SIZE_HEIGHT;

    }

    public static class Buttons{
        public static class Button{
            public static final int ON = 0;
            public static final int OVER = 1;
            public static final int PRESSED = 2;
        }
        public static class MenuButtons {
            public static final int COUNT_BUTTONS = 1;
            public static final int PLAY = 0;
            public static final int OPTIONS = 1;
            public static final int QUIT = 2;
            public static final int BUTTON_WIDTH_DEFAULT = 140;
            public static final int BUTTON_HEIGHT_DEFAULT = 56;
        }
    }

    public static final class TextureConstans {



        public static final class Menu{
            public static final String MENU_LOCATION_TEXTURES = "menu";
            public static final String MENU_ATLAS = "menu_atlas.png";
            public static final String MENU_BACKGROUND = "menu_background.png";
            public static final String MENU_BUTTONS = "menu_buttons.png";

        }

        public static final class Island{
            public static final String ISLAND_LOCATION_TEXTURES = "island";
            public static final String LVL_TEXTURES_PNG = "lvl_textures.png";
            public static final String LVL_BACKGROUND_PNG = "lvl_background.png";

        }

    }

    public static final class Animal{
        public static final class ProbabilityOfEating{
            public static final int WOLFeatFOX = 0;

        }
        public static final class Weight{
            public static final int WOLF_WEIGHT = 50;
            public static final int FOX_WEIGHT = 8;
            public static final int BEAR_WEIGHT = 500;
            public static final int EAGLE_WEIGHT = 6;
            public static final float RABBIT_WEIGHT = 2.0f;
            public static final int HORSE_WEIGHT = 400;
            public static final float MOUSE_WEIGHT = 0.05f;
            public static final int GOAT_WEIGHT = 60;
            public static final int SHEEP_WEIGHT = 70;
            public static final int DUCK_WEIGHT = 1;



        }

        public static final class MaxCountOnTheSameCell{
            public static final int WOLF_COUNT = 5;
            public static final int FOX_COUNT = 5;
            public static final int BEAR_COUNT = 3;
            public static final int EAGLE_COUNT = 7;
            public static final int RABBIT_COUNT = 15;
            public static final int HORSE_COUNT = 3;
            public static final int MOUSE_COUNT = 30;
            public static final int GOAT_COUNT = 15;
            public static final int SHEEP_COUNT = 15;
            public static final int DUCK_COUNT = 25;
        }

        public static final class Speed{
            public static final int WOLF_SPEED = 3;
            public static final int FOX_SPEED = 2;
            public static final int BEAR_SPEED = 2;
            public static final int EAGLE_SPEED = 3;
            public static final int RABBIT_SPEED = 2;
            public static final int HORSE_SPEED = 4;
            public static final int MOUSE_SPEED = 2;
            public static final int GOAT_SPEED = 3;
            public static final int SHEEP_SPEED = 3;
            public static final int DUCK_SPEED = 4;
        }

        public static final class SaturationKilos{
            public static final int WOLF_SATURATION = 8;
            public static final int FOX_SATURATION = 2;
            public static final int BEAR_SATURATION = 80;
            public static final int EAGLE_SATURATION = 1;
            public static final float RABBIT_SATURATION = 0.45f;
            public static final int HORSE_SATURATION = 60;
            public static final float MOUSE_SATURATION = 0.01f;
            public static final int GOAT_SATURATION = 10;
            public static final int SHEEP_SATURATION = 15;
            public static final float DUCK_SATURATION = 0.15f;
        }
    }

}
