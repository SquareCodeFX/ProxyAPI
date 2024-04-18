package net.square.settings;

import lombok.Builder;
import lombok.Getter;

/**
 * The ProxyCheckSettings class represents the settings for checking proxy information.
 * These settings include options for checking ASNs, nodes, time, ports, seen status, risk score, and time duration.
 */
@Builder
@Getter
public class ProxyCheckSettings {

    private boolean asn  = true;     // true, false
    private boolean node = true;    // true, false
    private boolean time = true;    // true, false
    private boolean port = true;    // true, false
    private boolean seen = true;    // true, false
    private boolean shrt = false;   // true, false

    private int vpn  = 3;            // 0, 1, 2, 3
    private int risk = 2;           // 0, 1, 2
    private int days = 3;           // 1, 2, 3, 4, 5, 6, 7
}
