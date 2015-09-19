package org.codebrewery;


public final class ApiConfig
{
    private final String host;
    private final String port;
    private final String apiLocation;

    private ApiConfig(final String host, final String port, final String apiLocation)
    {
        this.host = host;
        this.port = port;
        this.apiLocation = apiLocation;
    }

    public String getHost()
    {
        return this.host;
    }

    public String getPort()
    {
        return this.port;
    }

    public String getApiLocation()
    {
        return this.apiLocation;
    }

    public String getRequestBaseUrl() {
        return getHost() + ":" + getPort() + "/" + getApiLocation();
    }


    public static class ConfigBuilder
    {
        private  String nestedHost;
        private  String nestedPort;
        private  String nestedApiLocation;

        public ConfigBuilder()
        {
            this.nestedHost = null;
            this.nestedApiLocation = "api";
            this.nestedPort = "8080";
        }

        public ConfigBuilder port(final String nestedPost)
        {
            this.nestedPort = nestedPost;
            return this;
        }

        public ConfigBuilder apiLocation(final String apiLocation)
        {
            this.nestedApiLocation = apiLocation;
            return this;
        }

        public ConfigBuilder host(final String nestedHost)
        {
            this.nestedHost = nestedHost;
            return this;
        }


        public ApiConfig build()
        {
            return new ApiConfig(nestedHost, nestedPort, nestedApiLocation);
        }
    }
}