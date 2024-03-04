# PWKeeper

## Introduction
PWKeeper is a simple password manager application where you can store your username, password, and other important details of your online accounts. This application also includes an account system to allow storing data from different accounts by users. This project was programmed using Java as its programming language on the Eclipse IDE.

## Features
- <b>Random Password Generator</b>: You can generate passwords using the password generator button to use as an alternative to your common passwords.
- <b>Notes</b>: You can add notes or reminders to each account you add for important details (e.g., adding a note saying this account is your primary or for gaming, etc.).
- <b>Clean interface</b>: The application was built with a simple yet modern interface to ensure it adheres to modern design standards.
- <b>Search</b>: Easily search your accounts using the search bar, especially when you have many items added.

## Disclaimer
- This project was created as a requirement for our programming subject. With this project, our professor aims to assess our knowledge in creating an interactive interface for a Java application only. Thus, the implementation of data-related processing (e.g., random password generation, authentication system, etc.) isn't secure, and I highly don't recommend anyone to develop something similar to this.

## Limitations
- <b>Data stored is temporary</b>: This means all the data you store is not permanently stored anywhere else. Upon program termination, all data will be deleted since this application doesn't use any database to store data. I haven't yet learned anything about databases since this project by our professor focuses only on our knowledge about creating a simple and interactive Java program, and it was suggested that implementing a database in our systems isn't mandatory and recommended so we can focus solely on what we're taught about creating interactive GUI.
- <b>Password generation isn't tested for reliablity</b>: The password generation of this application does not guarantee the most secure, robust, and best results. As I have researched, there are many security considerations when developing a password generator. If you will try to recreate this method, this implementation is not higly recommended.
- <b>Unsecure data storage implementation</b>:  The account credentials, as well as the account details you store in the program, are stored in plaintext, which is a bad practice. Additionally, there is no encryption or hashing in place, so everything can be easily read. Thus, this implementation isn't secure.
- <b>Insecure account authentication system</b>: The logic for account authentication system (e.g., logging in, registering an account, adding account item details to an account) isn't also secured.

## Credits/References
- [Haikei](https://haikei.app/) - For graphical image on homepage
