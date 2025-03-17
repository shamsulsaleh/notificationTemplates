# notificationTemplates
Email/SMS Notification Templates (Micro-service)

# Information

- Its not an email server. This is email template manipulation
- Using thymeleaf to construct the notification templates
- can be use for sms, just use thymeleaf plain string
- attachment support 


# Command
```curl --location 'http://localhost:9011/email/buildEmailTemplate' \
--form 'emailReq="{\"attmntExist\": \"true\", \"channel\": \"EMAIL\", \"emailBcc\": [\"ahmadaryanshafee@gmail.com\"], \"emailCc\": [\"shamsul.saleh@gmail.com\"], \"emailFrom\": [\"svcdel@email.local\"], \"emailTo\": [\"shamsul.saleh@irubictechsolutions.com\"], \"filesName\": [\"file1.doc\"], \"parameters\": {\"customer\": {\"companyName\": \"Shamsul Pvt Ltd\", \"name\": \"Shamsul\"}, \"servicework\": {\"workRefId\": \"Test123455\"}}, \"subject\": \"Subject test\", \"templateName\": \"CompletWork\"}"'
```


# Request Structure
```
{
    "attmntExist": "true",
    "channel": "EMAIL",
    "emailBcc": [
        "ahmadaryanshafee@gmail.com"
    ],
    "emailCc": [
        "shamsul.saleh@gmail.com"
    ],
    "emailFrom": [
        "svcdel@email.local"
    ],
    "emailTo": [
        "shamsul.saleh@irubictechsolutions.com"
    ],
    "filesName": [
        "file1.doc"
    ],
    "parameters": {
        "customer": {
            "companyName": "Shamsul Pvt Ltd",
            "name": "Shamsul"
        },
        "servicework": {
            "workRefId": "Test123455"
        }
    },
    "subject": "Subject test",
    "templateName": "CompleteWork"
}
```


# Additional reference
[Thymelead reference](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#what-is-thymeleaf)
