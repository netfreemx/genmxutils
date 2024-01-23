/*
 * Created by Cristian Gonzalez on 15/12/23 13:35
 *  Copyright (c) NetFreeMexico 2023 . All rights reserved.
 */

package com.netfreemexico.generador.constants;

public interface AppConstants {
    String SERVER_POSITION = "LastSelectedServer",
            TUNNELTYPE_KEY = "tunnelType";

    String UDP_SERVER = "udpserver",
            UDP_AUTH = "udpauth",
            UDP_OBFS = "udpobfs",
            UDP_DOWN = "udpdown",
            UDP_UP = "udpup",
            UDP_PORT = "udpport",
            V2RAY_JSON = "v2rayjson";

    String SERVIDOR_KEY = "sshServer",
            SERVIDOR_PORTA_KEY = "sshPort",
            USUARIO_KEY = "sshUser",
            SENHA_KEY = "sshPass",
            CHAVE_KEY = "chaveKey",
            NAMESERVER_KEY = "serverNameKey",
            DNS_KEY = "dnsKey",
            CUSTOM_SNI = "wsPayload";

    String PROXY_IP_KEY = "proxyRemoto",
            PROXY_PORTA_KEY = "proxyRemotoPorta",
            CUSTOM_PAYLOAD_KEY = "proxyPayload",
            USER_HWID = "userorhwid",
            USER_LOGIN = "userlogin",
            PASSW_LOGIN = "passwlogin",
            PROXY_USAR_DEFAULT_PAYLOAD = "usarDefaultPayload";

    int SERVER_TYPE_SSH = 0,
            SERVER_TYPE_SLOWDNS = 1,
            SERVER_TYPE_UDP = 2,
            SERVER_TYPE_V2RAY = 3;

    int TUNNEL_TYPE_DIRECT = 0,
            TUNNEL_TYPE_PROXY = 1,
            TUNNEL_TYPE_SSHSSL = 2,
            TUNNEL_TYPE_SSLPAYLOAD = 3;

    int bTUNNEL_TYPE_SSH_DIRECT = 1,
            bTUNNEL_TYPE_SSH_PROXY = 2,
            bTUNNEL_TYPE_SSH_SSL = 3,
            bTUNNEL_TYPE_SSL_PAYLOAD = 4,
            bTUNNEL_TYPE_SLOWDNS = 5,
            bTUNNEL_TYPE_UDP = 6,
            bTUNNEL_TYPE_V2RAY = 7;
}
