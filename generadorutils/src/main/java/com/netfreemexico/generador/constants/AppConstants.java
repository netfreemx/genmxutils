/*
 * Created by Cristian Gonzalez on 15/12/23 13:35
 *  Copyright (c) NetFreeMexico 2023 . All rights reserved.
 */

package com.netfreemexico.generador.constants;

public interface AppConstants {
    public static final String SERVER_POSITION = "LastSelectedServer",
            TUNNELTYPE_KEY = "tunnelType";

    public static final String
            UDP_SERVER = "udpserver",
            UDP_AUTH = "udpauth",
            UDP_OBFS = "udpobfs",
            UDP_DOWN = "udpdown",
            UDP_UP = "udpup",
            UDP_BUFFER = "udpbuffer",
            V2RAY_JSON = "v2rayjson";
    ;

    public static final String
            SERVIDOR_KEY = "sshServer",
            SERVIDOR_PORTA_KEY = "sshPort",
            USUARIO_KEY = "sshUser",
            SENHA_KEY = "sshPass",
            CHAVE_KEY = "chaveKey",
            NAMESERVER_KEY = "serverNameKey",
            DNS_KEY = "dnsKey",
            KEYPATH_KEY = "keyPath",
            PORTA_LOCAL_KEY = "sshPortaLocal",
            PINGER_KEY = "pingerSSH",
            CUSTOM_SNI = "wsPayload";

    public static final String PROXY_IP_KEY = "proxyRemoto",
    PROXY_PORTA_KEY = "proxyRemotoPorta",
    CUSTOM_PAYLOAD_KEY = "proxyPayload",
            USER_HWID = "userorhwid",
            USER_LOGIN = "userlogin",
            PASSW_LOGIN = "passwlogin",
    PROXY_USAR_DEFAULT_PAYLOAD = "usarDefaultPayload",
    PROXY_USAR_AUTENTICACAO_KEY = "usarProxyAutenticacao"
    ;

    public static final int SERVER_TYPE_SSH = 0,
    SERVER_TYPE_SLOWDNS = 1,
    SERVER_TYPE_UDP = 2,
    SERVER_TYPE_V2RAY = 3;

    public static final int TUNNEL_TYPE_DIRECT = 0,
    TUNNEL_TYPE_PROXY = 1,
    TUNNEL_TYPE_SSHSSL = 2,
    TUNNEL_TYPE_SSLPAYLOAD = 3;

    public static final int
            bTUNNEL_TYPE_SSH_DIRECT = 1,
            bTUNNEL_TYPE_SSH_PROXY = 2,
            bTUNNEL_TYPE_SSH_SSL = 3,
            bTUNNEL_TYPE_SSL_PAYLOAD = 4,
            bTUNNEL_TYPE_SLOWDNS = 5,
            bTUNNEL_TYPE_UDP = 6,
            bTUNNEL_TYPE_V2RAY = 7;
    ;
}
