@import gg.jte.Content
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My website</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<header class="bg-light p-3">
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="/">Main page</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/users">All users</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<main class="container mt-4">
    ${content}
</main>

<footer class="bg-light text-center p-3 mt-4">
    <p><a href="https://github.com/Shturman13/hexlet-git" target="_blank">My GitHub Page</a></p>
</footer>
</body>
</html>

