官网地址：
<a name="h8B3h"></a>
## 1构建项目
html构建
```
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div id="like-button-root"></div>
</body>
 end of the page
<!--定义 React 组件。-->
<script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>、
<!--React 将 HTML 元素渲染到DOM。-->
<script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>
<!--jsx表达式-->
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script  type="text/babel">
    function LikeButton() {
        const [liked, setLiked] = React.useState(false);

        if (liked) {
            return 'You liked this!';
        }

        // return React.createElement(
        //     'button',
        //     {
        //         onClick: () => setLiked(true),
        //     },
        //     'Like'
        // );
        //jsx表达式
        return (
            <button onClick={() => setLiked(true)}>
                Like
            </button>
        );
    }
        const rootNode = document.getElementById('like-button-root');
        const root = ReactDOM.createRoot(rootNode);
        root.render(React.createElement(LikeButton));
</script>
</html>

```
npm启动
```
npx create-react-app my-app
cd my-app
npm start
```

本文不在概述：如何使用官方地址十分详细
