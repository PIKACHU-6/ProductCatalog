
# Product Catalog Management


## Build and Run Project

Build and run the app using maven

```bash
  mvm package
  java -jar target/ProductCatalog-0.0.1-SNAPSHOT.jar
```
    The app will start running at http://localhost:8080
## API Reference

#### 1) Insert Product

```http
  POST /v1/products

 
    Sample Request Body: 
    {
        "name": "Green Shirt",
        "description": "Red hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "red",
            "shirt",
            "slim fit"
        ],
        "category" : "apparel"
    }

```



#### 2) Get products

```http
  GET /V1/products/{category}?pageSize=5&pageNo=0
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `category`      | `string` | **Required**. category of item to fetch |
| `pageSize`       | `Integer` | Max entries per page (Defualt:5)  |
| `pageNo`       | `Integer` | Page number. (Defualt:0) |



  