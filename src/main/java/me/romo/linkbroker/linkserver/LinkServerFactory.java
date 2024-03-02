package me.romo.linkbroker.linkserver;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class LinkServerFactory {

    private final Map<InetSocketAddress, LinkServer> linkServers = new HashMap<>();

    private final Map<String, LinkServer> linkServersByName = new HashMap<>();

    private final Map<InetSocketAddress, LinkServer> linkServersByPublicAddress = new HashMap<>();

    public void addLinkServer(LinkServer linkServer){
        this.linkServers.put(linkServer.getRemoteAddress(), linkServer);
        matchLinkServerName(linkServer);
        matchLinkServerPublicAddress(linkServer);
    }

    public void onDisconnectLinkServer(LinkServer linkServer){
        this.linkServers.remove(linkServer.getRemoteAddress());
        unMatchLinkServerName(linkServer);
        unMatchLinkServerPublicAddress(linkServer);
    }

    public LinkServer getLinkServerByName(String name){
        return linkServersByName.get(name);
    }

    public LinkServer getLinkServerByPublicAddress(InetSocketAddress publicAddress){
        return linkServersByPublicAddress.get(publicAddress);
    }

    public void matchLinkServerName(LinkServer linkServer){
        if(linkServer.getName() == null){
            return;
        }
        this.linkServersByName.put(linkServer.getName(), linkServer);
    }

    public void unMatchLinkServerName(LinkServer linkServer){
        if(linkServer.getName() == null){
            return;
        }
        this.linkServersByName.remove(linkServer.getName());
    }

    public void matchLinkServerPublicAddress(LinkServer linkServer){
        if(linkServer.getPublicAddress() == null){
            return;
        }
        this.linkServersByPublicAddress.put(linkServer.getPublicAddress(), linkServer);
    }

    public void unMatchLinkServerPublicAddress(LinkServer linkServer){
        if(linkServer.getPublicAddress() == null){
            return;
        }
        this.linkServersByPublicAddress.remove(linkServer.getPublicAddress());
    }


}
