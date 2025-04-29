@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
    <head>
        <title>List of Users</title>
    </head>
    <body>
       <h1>${page.getHeader()}</h1>
       @if(page.getUsers().isEmpty())
           <p>Пока не добавлено ни одного пользователя</p>
       @else
           @for(var user : page.getUsers())
             <div>
              <h2>ID: ${user.getId()}</h2>
              <p>Name: ${user.getFirstName()} ${user.getLastName()}</p>
              <p>Email: ${user.getEmail()}</p>
              <p><a href="/users/${user.getId()}">View Details</a></p>
             </div>
           @endfor
       @endif
    </body>
</html>

