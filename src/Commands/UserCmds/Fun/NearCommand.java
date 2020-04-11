//package Commands.UserCmds.Fun;
//
//import org.bukkit.Bukkit;
//import org.bukkit.World;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.entity.Player;
//import utils.Files.Messages;
//import utils.Files.Permissions;
//import utils.Utils;
//
//public class NearCommand implements CommandExecutor {
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//        FileConfiguration p = Permissions.get();
//        FileConfiguration m = Messages.get();
//
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//            Player target = Bukkit.getPlayer(args[0]);
//            if (player.hasPermission(p.getString("Near")) || player.isOp()) {
//
//                int x = player.getLocation().getBlockX();
//                int y = player.getLocation().getBlockY();
//                int z = player.getLocation().getBlockZ();
//                World w = player.getWorld();
//
//                player.sendMessage(Utils.chat(m.getString("Near").replace("%target%", target())));
//
//
//            }
//
//        }
//
//        return false;
//    }
//}
