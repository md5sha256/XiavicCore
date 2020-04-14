//package Commands.StaffCmds.noncheat;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import static Main.mainClass.messages;
//import static Main.mainClass.permissions;
//
//public class TPPosCommand implements CommandExecutor {
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//            if (player.hasPermission(permissions.getString("TPPos")) || player.isOp()) {
//                if (args.length == 3) {
//                    Player LocX = Bukkit.getPlayer(args[0]);
//                    Player LocY = Bukkit.getPlayer(args[0]);
//                    Player LocZ = Bukkit.getPlayer(args[0]);
//                    switch (args.length) {
//                        case 1:
//
//                    }
//                }
//            }
//        }
//        return false;
//    }
//}
