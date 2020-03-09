package com.tala.kafkaingress.schemaregistry.client;

import java.util.Arrays;
import java.util.List;

public class Versions {

    public static final String SCHEMA_REGISTRY_V1_JSON = "application/vnd.schemaregistry.v1+json";
    // Default weight = 1
    public static final String SCHEMA_REGISTRY_V1_JSON_WEIGHTED = SCHEMA_REGISTRY_V1_JSON;
    // These are defaults that track the most recent API version. These should always be specified
    // anywhere the latest version is produced/consumed.
    public static final String SCHEMA_REGISTRY_MOST_SPECIFIC_DEFAULT = SCHEMA_REGISTRY_V1_JSON;
    public static final String SCHEMA_REGISTRY_DEFAULT_JSON = "application/vnd.schemaregistry+json";
    public static final String SCHEMA_REGISTRY_DEFAULT_JSON_WEIGHTED =
            SCHEMA_REGISTRY_DEFAULT_JSON
                    + "; qs=0.9";
    public static final String JSON = "application/json";
    public static final String JSON_WEIGHTED = JSON + "; qs=0.5";

    public static final List<String> PREFERRED_RESPONSE_TYPES = Arrays
            .asList(io.confluent.kafka.schemaregistry.client.rest.Versions.SCHEMA_REGISTRY_V1_JSON,
                    io.confluent.kafka.schemaregistry.client.rest.Versions.SCHEMA_REGISTRY_DEFAULT_JSON,
                    io.confluent.kafka.schemaregistry.client.rest.Versions.JSON);

    // This type is completely generic and carries no actual information about the type of data, but
    // it is the default for request entities if no content type is specified. Well behaving users
    // of the API will always specify the content type, but ad hoc use may omit it. We treat this as
    // JSON since that's all we currently support.
    public static final String GENERIC_REQUEST = "application/octet-stream";

}
