package net.square.settings;

import lombok.Builder;
import lombok.Getter;

@SuppressWarnings("UnusedAssignment")
@Builder
@Getter
public class ProxyCheckSettings {

    private boolean asn = true;     // true, false
    private boolean node = true;    // true, false
    private boolean time = true;    // true, false
    private boolean port = true;    // true, false
    private boolean seen = true;    // true, false
    private boolean shrt = false;   // true, false

    private int vpn = 3;            // 0, 1, 2, 3
    private int risk = 2;           // 0, 1, 2
    private int days = 3;           // 1, 2, 3, 4, 5, 6, 7
}
