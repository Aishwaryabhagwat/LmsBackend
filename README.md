# LMS Backend Deployment Guide

## Overview

This document provides step-by-step instructions to build and deploy the LMS Backend (Spring Boot) application on the Paramrudra Cluster using systemd service, HTTPS, and Apache reverse proxy.

---

## Tech Stack

- Java8
- Spring Boot
- MySQL8
- Apache HTTP Server (Reverse Proxy)
- Linux (RHEL/CentOS)

---

## Build the Application

Run Maven build:

```bash
mvn clean package -DskipTests
 Output File
target/E-Learning-0.0.1-SNAPSHOT.war

 Deployment Steps

1. Copy WAR to Server
scp -P 4422 "<LOCAL_PATH>/E-Learning-0.0.1-SNAPSHOT.war" cadmin@paramrudra.iuac.res.in:/home/cadmin/aishwarya/LMS

2. Move WAR to Deployment Directory
sudo mkdir -p /opt/LMS
sudo mv /home/cadmin/aishwarya/LMS/E-Learning-0.0.1-SNAPSHOT.war /opt/LMS/

3. Create Start Script

File:
/opt/LMS/lms.sh
#!/bin/bash
java -jar /opt/LMS/E-Learning-0.0.1-SNAPSHOT.war

Make executable:
sudo chmod +x /opt/LMS/lms.sh

4. Create Systemd Service
File:

/etc/systemd/system/lms.service
[Unit]
Description=LMS Backend Service
After=network.target

[Service]
Type=simple
ExecStart=/opt/LMS/lms.sh
Restart=on-failure
RestartSec=60s
Environment=SPRING_PROFILES_ACTIVE=prod

[Install]
WantedBy=multi-user.target

5. Start Service
sudo systemctl daemon-reload
sudo systemctl enable lms.service
sudo systemctl start lms.service
sudo systemctl status lms.service


📁 File Storage Setup
sudo mkdir -p /opt/LMS/{images,notes,assignments,userAnswers,videos}
sudo chown -R apache:apache /opt/LMS/
sudo chmod -R 775 /opt/LMS/


🔗 Static Resource Mapping (Spring Boot)

Add in configuration:

registry.addResourceHandler("/images/**")
        .addResourceLocations("file:/opt/LMS/images/");

registry.addResourceHandler("/notes/**")
        .addResourceLocations("file:/opt/LMS/notes/");


🔐 HTTPS Configuration
Option 1: Self-Signed Certificate
keytool -genkeypair -alias springboot -keyalg RSA -keysize 4096 \
-storetype PKCS12 -keystore springboot.p12 -validity 3650

Option 2: Use Existing Certificate
sudo openssl pkcs12 -export \
-in /etc/ssl/certs/cdacdelhi.cert \
-inkey /etc/ssl/private/private.key \
-name springboot \
-out /opt/LMS/springboot.p12

Configure in application.properties
server.ssl.enabled=true
server.ssl.key-store=/opt/LMS/springboot.p12
server.ssl.key-store-password=*****
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=springboot



 Database Setup
CREATE DATABASE LMS;
Sample Users
INSERT INTO user (id, email, is_active, name, password, username)
VALUES (1, 'admin@cdac.in', 1, 'admin', '<encrypted>', 'admin1');

INSERT INTO roles (user_id, role) VALUES (1, 'ROLE_ADMIN');


🌐 Base URL
https://paramrudra.cdacdelhi.in/lms-backend/
```
