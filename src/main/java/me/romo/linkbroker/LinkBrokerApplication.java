package me.romo.linkbroker;

import me.romo.linkbroker.linkserver.LinkServerFactory;
import me.romo.linkbroker.protocol.PacketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class LinkBrokerApplication {

    private static final Logger logger = LoggerFactory.getLogger("LinkBrokerApplication");

    private static String password;
    private static int port;

    private static PacketFactory packetFactory;
    private static LinkServerFactory linkServerFactory;

    private static LinkSocket linkSocket;

    private static final int protocolVersion = 1;

    public static void main(String[] args) {
        SpringApplication.run(LinkBrokerApplication.class, args);
        loadConfig();

        packetFactory = new PacketFactory();
        linkServerFactory = new LinkServerFactory();
        linkSocket = new LinkSocket(getPort());
    }

    public static void loadConfig(){
        ClassPathResource resource = new ClassPathResource("config.yml");
        try {
            File file = new File(System.getProperty("user.dir"), "config.yml");
            if (!file.exists()) {
                Files.copy(resource.getInputStream(), Paths.get(file.getPath()));
            }

            //LOAD
            Map<String, Object> data = new Yaml().load(new FileReader(file));
            password = String.valueOf(data.get("password"));
            port = (int) data.get("port");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    public static LinkSocket getLinkSocket() {
        return linkSocket;
    }

    public static String getPassword() {
        return password;
    }

    public static int getPort() {
        return port;
    }

    public static PacketFactory getPacketFactory() {
        return packetFactory;
    }

    public static LinkServerFactory getLinkServerFactory() {
        return linkServerFactory;
    }

    public static int getProtocolVersion() {
        return protocolVersion;
    }
}
