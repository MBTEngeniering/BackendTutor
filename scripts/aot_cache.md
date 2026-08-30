# Training und Assembly in einem Schritt
java -XX:AOTCacheOutput=build/app.aot -cp build/app.jar com.example.App

# Deployment
java -XX:AOTCache=build/app.aot -cp build/app.jar com.example.App

