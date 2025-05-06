@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes

@param PostsPage page

@template.layout.page(
content = @`
    <div class="container mt-4">
        <h1>Список постов</h1>

        <div class="list-group mb-4">
            @for(var post : page.getPosts())
                <a href="${NamedRoutes.postPath(post.getId())}" class="list-group-item list-group-item-action">
                    ${post.getName()}
                </a>
            @endfor
        </div>

        <div class="d-flex justify-content-between">
            @if(page.getCurrentPage() > 1)
                <a href="${NamedRoutes.postsPath(page.getCurrentPage() - 1)}" class="btn btn-primary">Назад</a>
            @else
                <a class="btn btn-primary disabled">Назад</a>
            @endif

            <a href="${NamedRoutes.postsPath(page.getCurrentPage() + 1)}" class="btn btn-primary">Вперед</a>
        </div>
    </div>
`
)

