<a id="readme-top"></a>
# Introduction
  Hello. That's my first <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"></img>  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"></img> MVC project and it is for my SOFTUNI Final Exam.

## :ledger: Index

- [About](#beginner-about)
- [Usage](#zap-usage)
- [REST API](#wrench-rest-api)
- [Installation](#electric_plug-installation)
- [Built With](#star-built-with)

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

##  :star: Built With

* <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"></img>
* <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"></img>
* <img src="https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E"></img>
* <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"></img>
* <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"></img>
* <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"></img>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## ❗ Additional Information
Unfortunately, the time wasn’t enough to finish the project as I had hoped. There are still some functions that need to be added or fixed, and the UI needs improvement in some areas.
<p align="right">(<a href="#readme-top">back to top</a>)</p>




