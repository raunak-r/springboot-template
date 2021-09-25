# SpringBoot Application Code Writing Guidelines

## Project Structure 
```text
The project is based on by-feature implementation and not a
by-layer implementation.
Read more here - https://phauer.com/2020/package-by-feature/

├── feature1
│   ├── Feature1Controller
│   ├── Feature1DAO
│   ├── Feature1Client
│   ├── Feature1DTOs.kt
│   ├── Feature1Entities.kt
│   └── Feature1Configuration
├── feature2
├── feature3
└── common
```

## Feature Structures
```text
Each feature should contain folders in the following manners.
1. entity
2. controllers
3. repository
```

## API Structure
```text
All urls' should follow the following format
- domain/{feature_folder_name}/{controller_name_as_a_noun}/

As per the above example, it would be
- domain/feature1/clients/
- domain/feature1/entities/
... etc.
```

## Pre-Req to run this Project
```text
```