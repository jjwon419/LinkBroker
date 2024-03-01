package me.romo.linkbroker.linkserver;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class LinkServerFactory {

    private final Map<InetSocketAddress, LinkServer> linkServers = new HashMap<>();

    public void addLinkServer(LinkServer linkServer){
        this.linkServers.put(linkServer.getAddress(), linkServer);
    }
}
