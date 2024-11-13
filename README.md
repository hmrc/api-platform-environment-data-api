
# api-platform-environment-data-api

This API provides an interface to API Platform data to be consumed by other MDTP services.

Note that in general we advise you to use api-platform-admin-api.  This API is provided in order to enable limited access to application and API data in subordinate environments (i.e. Development and External Test).  Developer data is not available via this API.

It provides stable endpoints for consumers and allows for free growth and maintenance of the API Platform services.

The details of the endpoints can be found in the [OpenAPI Specification](./conf/api-platform-environment-data-api.yaml).

## Running locally
To run the service locally, start all the dependant services by running:

```bash
./run_local_with_dependencies.sh
```

This service uses internal auth to secure its endpoints.

Add an authorisation token of '15506' by running:

```bash
curl -X POST "http://localhost:8470/test-only/token" \
-H "content-type: application/json" \
-d "{
  \"token\": \"15506\",
  \"principal\": \"api-platform-environment-data-api-local-test\",
  \"permissions\": [{
     \"resourceType\": \"api-platform-environment-data-api\",
     \"resourceLocation\": \"*\",
     \"actions\": [ \"READ\" ]
  }]
}"
```

This token will remain in your local database for a year, so does not have to be generated every time.

Then call an endpoint such as:

```bash
curl "http://localhost:15506/applications?clientId=:clientId" -H "Authorization: 15506"
```

Use one of your local clientIds for the query parameter `:clientId`.

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").