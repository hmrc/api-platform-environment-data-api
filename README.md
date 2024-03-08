
# api-platform-environment-data-api

This API provides an interface to API Platform data to be consumed by other MDTP services.

Note that in general we advise you to use api-platform-admin-api.  This API is provided in order to enable limited access to application and API data in subordinate environments (i.e. Development and External Test).  Developer data is not available via this API.

It provides stable endpoints for consumers and allows for free growth and maintenance of the API Platform services.

The details of the endpoints can be found in the [OpenAPI Specification](./conf/api-platform-environment-data-api.yaml).

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").