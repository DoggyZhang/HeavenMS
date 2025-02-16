package tools;

import client.MapleCharacter;
import client.MapleClient;
import client.inventory.Item;
import net.server.Server;
import server.MapleItemInformationProvider;
import server.MapleTrade;
import server.expeditions.MapleExpedition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogHelper {

    public static void logTrade(MapleTrade trade1, MapleTrade trade2) {
        String name1 = trade1.getChr().getName();
        String name2 = trade2.getChr().getName();
        String log = "TRADE BETWEEN " + name1 + " AND " + name2 + "\r\n";
        //Trade 1 to trade 2
        log += trade1.getExchangeMesos() + " mesos from " + name1 + " to " + name2 + " \r\n";
        for (Item item : trade1.getItems()) {
            String itemName = MapleItemInformationProvider.getInstance().getName(item.getItemId()) + "(" + item.getItemId() + ")";
            log += item.getQuantity() + " " + itemName + " from " + name1 + " to " + name2 + " \r\n";
        }
        //Trade 2 to trade 1
        log += trade2.getExchangeMesos() + " mesos from " + name2 + " to " + name1 + " \r\n";
        for (Item item : trade2.getItems()) {
            String itemName = MapleItemInformationProvider.getInstance().getName(item.getItemId()) + "(" + item.getItemId() + ")";
            log += item.getQuantity() + " " + itemName + " from " + name2 + " to " + name1 + " \r\n";
        }
        log += "\r\n\r\n";
        FilePrinter.print(FilePrinter.LOG_TRADE, log);
    }

    public static void logExpedition(MapleExpedition expedition) {
        Server.getInstance().broadcastGMMessage(expedition.getLeader().getWorld(), MaplePacketCreator.serverNotice(6, expedition.getType().toString() + " Expedition with leader " + expedition.getLeader().getName() + " finished after " + getTimeString(expedition.getStartTime())));

        String log = expedition.getType().toString() + " EXPEDITION\r\n";
        log += getTimeString(expedition.getStartTime()) + "\r\n";

        for (String memberName : expedition.getMembers().values()) {
            log += ">>" + memberName + "\r\n";
        }
        log += "BOSS KILLS\r\n";
        for (String message : expedition.getBossLogs()) {
            log += message;
        }
        log += "\r\n";
        FilePrinter.print(FilePrinter.LOG_EXPEDITION, log);
    }

    public static String getTimeString(long then) {
        long duration = System.currentTimeMillis() - then;
        int seconds = (int) (duration / 1000) % 60;
        int minutes = (int) ((duration / (1000 * 60)) % 60);
        return minutes + " Minutes and " + seconds + " Seconds";
    }

    public static void logLeaf(MapleCharacter player, boolean gotPrize, String operation) {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
        String log = player.getName() + (gotPrize ? " used a maple leaf to buy " + operation : " redeemed " + operation + " VP for a leaf") + " - " + timeStamp;
        FilePrinter.print(FilePrinter.LOG_LEAF, log);
    }

    public static void logGacha(MapleCharacter player, int itemid, String map) {
        String itemName = MapleItemInformationProvider.getInstance().getName(itemid);
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
        String log = player.getName() + " got a " + itemName + "(" + itemid + ") from the " + map + " gachapon. - " + timeStamp;
        FilePrinter.print(FilePrinter.LOG_GACHAPON, log);
    }

    public static void logChat(MapleClient player, String chatType, String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        FilePrinter.print(FilePrinter.LOG_CHAT, "[" + sdf.format(Calendar.getInstance().getTime()) + "] (" + chatType + ") " + player.getPlayer().getName() + ": " + text);
    }

}
