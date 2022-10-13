package quantee.pgmstats;

import net.minecraft.util.EnumChatFormatting;

public class BoolRenderer {
    public static String formatBool (Boolean condition) {
        if (condition) {
            return EnumChatFormatting.BLUE + "True";
        }
        else {
            return EnumChatFormatting.RED + "False";
        }
    }

    public static String edFormatBool (Boolean condition) {
        if (condition) {
            return EnumChatFormatting.BLUE + "Enabled";
        }
        else {
            return EnumChatFormatting.RED + "Disabled";
        }
    }
}
