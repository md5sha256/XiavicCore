//package Commands.UserCmds.Fun;
//
//import org.bukkit.Location;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//
//import static Main.mainClass.messages;
//import static Main.mainClass.permissions;
//
//public class TopCommand implements CommandExecutor {
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//            if (player.hasPermission(permissions.getString("Top"))) {
//                int locX = player.getLocation().getBlockY();
//                int locZ = player.getLocation().getBlockZ();
//                int locY = player.getWorld().getHighestBlockYAt(locX, locZ);
//                Location top = new Location(player.getWorld(), locX, locY, locZ);
//                player.teleport(top);
//                return true;
//            } else {
//                player.sendMessage(messages.getString("NoPerms"));
//            }
//        }
//        return false;
//    }
//}
