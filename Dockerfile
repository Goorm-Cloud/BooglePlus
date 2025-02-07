# Base image
FROM eclipse-temurin:17-jdk AS build

# Maintainer info
LABEL maintainer="hyunjunson"

# Set the working directory
WORKDIR /app

# Copy Gradle Wrapper and dependencies to cache layers
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# Copy the rest of the application
COPY . .

# Build the application
RUN ./gradlew build -x test --no-daemon

# Use a lightweight JDK runtime image for the final container
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy built jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set environment variables (MySQL connection)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/BooglePlus?serverTimezone=UTC&characterEncoding=UTF-8
ENV SPRING_DATASOURCE_USERNAME=boogleplus
ENV SPRING_DATASOURCE_PASSWORD=00000000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]