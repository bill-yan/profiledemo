# profiledemo
A simple demo on using `Spring boot` + `JPA` + `graphql`

# Run the demo

```
gradle :bootRun
```
then naviate your browser to:
```
http://localhost:9977/
```
you will see the graphiQL UI

## Sample query
```
{
  listAllEnabledLocales {
    id
    name
    locale
  }
  listAllTimezones{
    id
    name
    
  }
 userById(id:1) {
  fullName
  jobTitle
  defaultLocale {
   locale
  }
  timezone {
   id
   name
  }
  emails {
   id
   email
   primaryEmail
  }
  phones {
   id
   number
   primaryPhone
  }
 }
}
```

## Sample mutation

```
{
 createEmail(newEmail:
  {email:"abc@abc.com",
   notificationEnabled:true,
   user:{id:1}
  }
 ){
  id
  email
  primaryEmail
 }
}
```
