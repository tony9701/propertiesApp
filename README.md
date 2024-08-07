<a id="readme-top"></a>
# Introduction
  Hello. That's my first ![image](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) project and it is for my SOFTUNI Final Exam.


<h1><img src="https://github.com/user-attachments/assets/a2c2c5ab-f412-4fa0-8f46-0f77ca8f5712" width="48"> <br><strong>SOFTUNI</strong>, before starting the project, <a href="https://github.com/tony9701/messagesApi" targer="_blank">download</a> the REST API! - https://github.com/tony9701/messagesApi
<br><img src="https://github.com/user-attachments/assets/a2c2c5ab-f412-4fa0-8f46-0f77ca8f5712" width="48">



## :ledger: Index

- [About](#beginner-about)
- [Usage](#zap-usage)
  - [Installation](#electric_plug-installation)
  - [Commands](#package-commands)
- [Development](#wrench-development)
  - [Pre-Requisites](#notebook-pre-requisites)
  - [Developmen Environment](#nut_and_bolt-development-environment)
  - [File Structure](#file_folder-file-structure)
  - [Build](#hammer-build)  
  - [Deployment](#rocket-deployment)  
- [Community](#cherry_blossom-community)
  - [Contribution](#fire-contribution)
  - [Branches](#cactus-branches)
  - [Guideline](#exclamation-guideline)  
- [FAQ](#question-faq)
- [Resources](#page_facing_up-resources)
- [Gallery](#camera-gallery)
- [Credit/Acknowledgment](#star2-creditacknowledgment)
- [License](#lock-license)

##  :beginner: About
Welcome to PropertyApp! This application allows users to buy or rent properties and provides agents with the ability to sell or let properties.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## :zap: Usage
<h4>User Functionality:</h4>

- Browse available properties for sale or rent.
- View detailed information about each property.
- Contact the admin with the "Contact us" page.
<h4>Agent Functionality:</h4>

- List new properties for sale or rent.
- Manage ONLY their property listings.
- Access to the Agency Panel of the agency they are added.
<h4>Agency-Admin Functionality:</h4>

- Create agency and delete agency.
- View detailed information about each agent in his agency.
- List new properties for sale or rent.
- Add agents and remove them.
- Manage EVERY property the agency added.
- Access to the Agency Panel of the agency he created.
<h4>ADMIN Functionality:</h4>

- When you first start the app it will automatically register an ADMIN with email: admin@admin.com and password admin12.
- Access to the Admin Panel.
- View detailed information about each user in the DB.
- Access to the every Agency Panel.
- Remove all kind of users.
- Manage property listings.
- Access to the messages from "Contact Us" form.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## :wrench: REST API
Before starting the application, ensure the REST API is running. Download and set up the REST API from the following link: <a href="https://github.com/tony9701/messagesApi" targer="_blank">Download REST API</a>.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

##  :electric_plug: Installation

1. Clone the repo
   ```sh
   git clone https://github.com/tony9701/propertiesApp.git
   ```
2. Navigate to the project directory:
   ```sh
   cd propertiesApp
   ```
3. Install dependencies:
   ```sh
   npm install
   ```
4. You have to set up your DB settings in resources/application.yaml
   ```
   datasource:
    driverClassName: -DB driver here-
    url: - db connection url - 
    username: '${db_username}'
    password: '${db_password}'
   ```
   
4. Start the application:
   ```sh
   npm start
   ```
<p align="right">(<a href="#readme-top">back to top</a>)</p>
##  :camera: Gallery
Pictures of your project.
<p align="right">(<a href="#readme-top">back to top</a>)</p>
