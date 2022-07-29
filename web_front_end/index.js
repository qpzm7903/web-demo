
async function getData() {
    const res = await fetch('http://localhost:9090/test', {
        // mode: "no-cors",
    })
    console.log(res)
    const data = await res.json
    console.log(data)
    return data
}

getData().then(res => console.log(res))
