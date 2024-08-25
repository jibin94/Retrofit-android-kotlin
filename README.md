## Retrofit
[Retrofit](https://square.github.io/retrofit/) is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with [OkHttp](https://square.github.io/okhttp/).

This library makes downloading JSON or XML data from a web API fairly straightforward. Once the data is downloaded then it is parsed into a Plain Old Java Object (POJO) which must be defined for each "resource" in the response.

In the past, Retrofit relied on the Gson library to serialize and deserialize JSON data. Retrofit 2 now supports many different parsers for processing network response data, including Moshi, a library build by Square for efficient JSON parsing. However, there are a few limitations, so if you are not sure which one to choose, use the Gson converter for now.

| Converter  | Library                                                   |
|------------|-----------------------------------------------------------|
| Gson       | com.squareup.retrofit2:converter-gson:latest-version      |
| Jackson    | com.squareup.retrofit2:converter-jackson:latest-version   |
| Moshi      | com.squareup.retrofit2:converter-moshi:latest-version     |
| Protobuf   | com.squareup.retrofit2:converter-protobuf:latest-version  |
| Wire       | com.squareup.retrofit2:converter-wire:latest-version      |
| Simple XML | com.squareup.retrofit2:converter-simplexml:latest-version |

### Retrofit annotations

| Annotation | Description                                                                                                    |
|------------|----------------------------------------------------------------------------------------------------------------|
| @Path      | variable substitution for the API endpoint (i.e. username will be swapped for {username} in the URL endpoint). |
| @Query     | specifies the query key name with the value of the annotated parameter.                                        |
| @Body      | payload for the POST call (serialized from a Java object to a JSON string)                                     |
| @Header    | specifies the header with the value of the annotated parameter.                                                |

### Changing the base URL
Normally, the base URL is defined when you instantiated an Retrofit instance. Retrofit 2 allows you to override the base URL specified by changing it in the annotation (i.e. if you need to keep one specific endpoint using an older API endpoint)

```
@POST("https://api.github.com/api/v3")
```

