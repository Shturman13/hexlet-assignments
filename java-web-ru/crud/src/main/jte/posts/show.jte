@import exercise.model.Post
@import exercise.util.NamedRoutes

@param Post post

@template.layout.page(
content = @`
    <div class="container mt-4">
        <h1>${post.getName()}</h1>
        <p>${post.getBody()}</p>

        <a href="${NamedRoutes.postsPath()}" class="btn btn-primary">Вернуться к списку постов</a>
    </div>
`
    )

