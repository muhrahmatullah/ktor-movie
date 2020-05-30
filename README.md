# A very simple K-tor based REST API


This repo is other version of my previous golang movie rest API: https://github.com/muhrahmatullah/go_movie
> TODO : use database instead of List

## Quick Start


``` bash
clone the repository and open it in your preffered IDE (recommended using intellij)
```

``` bash
run the app
check the log and it will show the url with the port
ex: [main] INFO  Application - Responding at http://0.0.0.0:8080
```

## Endpoints

### Get All Movies
``` bash
GET movies
```
### Get Single Movie

``` bash
GET movie/{id}
```

### Delete Movie

``` bash
DELETE movie/{id}
```

### Create Movie

``` bash
POST movie/

# Request sample
{
 	"title": "Mari Pulang",
 	"rating": "5.6",
 	"year": 2019,
 	"actors": [{
 		"name": "Rahmatullah",
 		"age": 22
 	}]
 }
```

### Update Movie

``` bash
PUT movies/{id}

# Request sample
{
	"title": "Mari Pulang Bersama",
	"rating": "5.6",
	"year": 2019,
	"actor": [{
		"name": "Rahmatullah aka matx",
		"age": 22
	}]
}

```
### Author

Muh Rahmatullah
