import {useState} from "react";

function Square({index, click}) {
    const [value, setValue] = useState(null);
    return <button className="square" onClick={click}>{index}</button>
}

export default function Borad() {
    //定义数据并全部设置null
    const [squares, setSquares] = useState(Array(9).fill(null));
    //设置翻转boolean值
    const [isFilp, setIsFilp] = useState(true);

    //创建获胜数组
    const winner = [
        [0, 1, 2]
    ]
    //存储获胜者
    const [swing, setSwing] = useState(null);

    function handleClick(value) {
        const newArray = squares.slice();
        if (newArray[value] != null) {
            return
        }
        if (isFilp) {
            newArray[value] = "O"
        } else {
            newArray[value] = "X"
        }
        setSquares(newArray)
        setIsFilp(!isFilp)
        //判断是否获胜
        for (let winnerElement of winner) {
            debugger
            let isWinner = newArray[winnerElement[0]] === newArray[winnerElement[1]] && newArray[winnerElement[0]] === newArray[winnerElement[2]]
            if (isWinner) {
                setSwing(newArray[winnerElement[0]])
                setSquares(Array(9).fill(null))
                alert("游戏结束")
                return
            }
        }
    }

    return (
        <>
            <h1>winner方是{swing}</h1>
            <div className="one">
                <Square index={squares[0]} click={() => handleClick(0)}/>
                <Square index={squares[1]} click={() => handleClick(1)}/>
                <Square index={squares[2]} click={() => handleClick(2)}/>
            </div>
            <div className="two">
                <Square index={squares[3]} click={() => handleClick(3)}/>
                <Square index={squares[4]} click={() => handleClick(4)}/>
                <Square index={squares[5]} click={() => handleClick(5)}/>
            </div>
            <div className="three">
                <Square index={squares[6]} click={() => handleClick(6)}/>
                <Square index={squares[7]} click={() => handleClick(7)}/>
                <Square index={squares[8]} click={() => handleClick(8)}/>
            </div>

        </>
    )
}
