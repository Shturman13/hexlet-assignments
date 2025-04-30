@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    @for(var user : page.getUsers())
        <div>${user.getFirstName()} ${user.getLastName()}</div>
    @endfor
`
)
