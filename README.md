# Getting Started

### Installation
Requies java8+ 

### Run
Execute in terminal ./mvnw spring-boot:run

### Info
There was no detailed information how to represent 2d shapes. So i decided to do it via 2d points.

Points should be placed in order to form shape. There is no auto rotation/detection for shapes.

### Request examples

#### Square:
```chatinput
curl --location 'http://localhost:8080/geometry/2d/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "points": [
        {
            "x": 1.0,
            "y": 1.0
        },
        {
            "x": 1.0,
            "y": 3.0
        },
        {
            "x": 3.0,
            "y": 3.0
        },
        {
            "x": 3.0,
            "y": 1.0
        }
    ]
}'
```

#### Rectangle
```chatinput
curl --location 'http://localhost:8080/geometry/2d/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "points": [
        {
            "x": 1.0,
            "y": 1.0
        },
        {
            "x": 1.0,
            "y": 3.0
        },
        {
            "x": 4.0,
            "y": 3.0
        },
        {
            "x": 4.0,
            "y": 1.0
        }
    ]
}'
```

#### Triangle
```chatinput
curl --location 'http://localhost:8080/geometry/2d/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "points": [
        {
            "x": 1.0,
            "y": 1.0
        },
        {
            "x": 1.0,
            "y": 4.0
        },
        {
            "x": 5.0,
            "y": 1.0
        }
    ]
}''
```
#### Circle
```chatinput
curl --location 'http://localhost:8080/geometry/2d/calculate?detectCircle=true&isInscribed=true' \
--header 'Content-Type: application/json' \
--data '{
    "points": [
        {
            "x": 1.0,
            "y": 1.0
        },
        {
            "x": 1.0,
            "y": 4.0
        },
        {
            "x": 5.0,
            "y": 1.0
        }
    ]
}'
```
