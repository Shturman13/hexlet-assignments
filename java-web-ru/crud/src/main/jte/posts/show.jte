@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes

@param PostPage page

@template.layout.page(
content = @`
    <div class="container mt-4">
        <h1>${page.getPost().getName()}</h1>
        <p>${page.getPost().getBody()}</p>

        <a href="${NamedRoutes.postsPath()}" class="btn btn-primary">Вернуться к списку постов</a>
    </div>
`
    )

