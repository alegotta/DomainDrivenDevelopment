const baseUrl = "/ddd/api"
async function fetchData(url, method = "GET", data) {
    console.debug("Fetching", url, "via", method, "with", data)
    try {
        const response = await fetch(url, {
            method: method,
            mode: "cors",
            cache: "no-cache",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json",
            },
            redirect: "follow",
            referrerPolicy: "no-referrer",
            body: data,
        })

        if (!response.ok) throw new Error(`${response.status} code from server`)
        else return response.json()
    } catch (error) {
        console.error("Fetch error", error)
        return {}
    }
}
async function getResource(type) {
    return fetchData(`${baseUrl}/${type}`)
}
async function putResource(type, data) {
    return fetchData(`${baseUrl}/${type}`, "PUT", JSON.stringify(data))
}

const isObject = (obj) => {
    return Object.prototype.toString.call(obj) === '[object Object]';
}
const capitalize = (str) => {
    return str.toLowerCase().replace(/^\w/, (c) => c.toUpperCase());
}

function populate(tagId, data) {
    const tag = document.getElementById(tagId)
    data.forEach(elem => {
        if (isObject(elem[1]) || Array.isArray(elem[1])) {
            const nestedChild = document.createElement("span")
            nestedChild.id = tagId+elem[0]
            tag.appendChild(nestedChild)
            populate(nestedChild.id, elem[1])
        } else {
            const newChild = document.createElement("li")
            newChild.innerHTML = `${elem[0]}: ${elem[1]}`
            tag.appendChild(newChild)
        }
    })
}

function setForm(formId, route, postActions) {
    const form = document.getElementById(formId)
    form.onsubmit = async event => {
        event.preventDefault()
        let inputs = Array.from(document.querySelectorAll("input"))
            .filter(inp => inp.type === "text")
            .map(inp => [inp.name, inp.value])
            .reduce((result, [name, value]) => {
                result[name] = value
                return result
            }, {})
        postActions(inputs)
    }
}

function setStorage(key, value) {
    sessionStorage.setItem(key, JSON.stringify(value))
}
function getStorage(key) {
    const value = sessionStorage.getItem(key)
    if (value === null) return value
    else return JSON.parse(value)
}

function setHome() {
    setForm("loginForm", "student", data => {
        setStorage("student", data)
        window.location.replace("student.html")
    })
}

async function setStudent() {
    const student = await getResource(`student?id=${getStorage("student").id}`)
    const htmlStudent = document.getElementById("student")
    htmlStudent.innerHTML = `Name: ${student.name}<br>
                             ID: ${student.id}<br>
                             Contacts: ${student.contacts.map(contact => contact.countryCode+contact.number + "("+contact.type+")").join(", ")}`
}
async function setCourses() {
    const courses = await getResource(`courses?id=${getStorage("student").id}`)
    const htmlStudent = document.getElementById("courses")
    htmlStudent.innerHTML = courses.map(course => `Course: ${course.courseId} - Status: ${capitalize(course.status)}`).join("<br>")
}