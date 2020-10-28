# Test Task 1

### Company: Rockwell Automation

### Author: Miroslaw wilczek

# Running

There are scripts for automating compilation and execution process:

- `compile.sh` - compile project with installed MVN
- `compile-mac.sh` - execute `compile.sh` with voice message for both success and failure
- `run.sh` - run compiled project with default parameters
- `compile-and-run.sh` - execute `compile.sh` and when passed without errors execute `run.sh`
- `compile-and-run-mac.sh` - execute `compile-and-run.sh` with voice message for both success and failure

# REST API

Default base url: `http://localhost:8080`

- `/api/hello` - welcome message.
- `/api/mappings/list` - mappings list.
- `/api/mappings/size/name/{mappingName}` - returns range for `mappingName`. <br>
  **example:** `api/mappings/size/name/Animals`
- `/api/mappings/size/id/{mappingId}` - the same as previous, but uses order number from `/api/mappings/list`. <br>
  **example:** `/api/mappings/size/id/0`
- `/api/mappings/map/name/{mappingName}?numbers={numbers}&separator={separator}` - returns mapping for `mappingName` and requested `numbers`.
  Optional `separator` holds string which will join values (default `,`). <br>
  **example:** `/api/mappings/map/name/Animals?numbers=4,3,2,1` <br>
  **example:** `/api/mappings/map/name/Animals?numbers=4,3,2,1&separator=%20!%20`
- `/api/mappings/map/id/{mappingId}?numbers={numbers}&separator={separator}` - the same as previous, but uses order number from `/api/mappings/list`. <br>
  **example:** `/api/mappings/map/id/0?numbers=4,3,2,1` <br>
  **example:** `/api/mappings/map/id/0?numbers=4,3,2,1&separator=%20!%20`

# Configuration

- Mappings configuration: `/mapperWebApp/src/main/resources/mappings/mappings.json` - changes requires recompilation
