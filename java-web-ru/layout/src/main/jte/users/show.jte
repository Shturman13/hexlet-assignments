@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <h1>User Details</h1>
    <div>
        <h2>ID: ${page.getUser().getId()}</h2>
        <p>Name: ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p> <!-- Предполагается, что у вас есть метод getName() -->
        <p>Email: ${page.getUser().getEmail()}</p> <!-- Предполагается, что у вас есть метод getEmail() -->
        <!-- Добавьте другие поля по мере необходимости -->
    </div>
    <a href="/users">Back to users list</a>
`
)