const canvas = document.getElementById('cubeCanvas');
const ctx = canvas.getContext('2d');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const vertices = [
    [-1, -1, -1],
    [1, -1, -1],
    [1, 1, -1],
    [-1, 1, -1],
    [-1, -1, 1],
    [1, -1, 1],
    [1, 1, 1],
    [-1, 1, 1]
];

const edges = [
    [0, 1], [1, 2], [2, 3], [3, 0],
    [4, 5], [5, 6], [6, 7], [7, 4],
    [0, 4], [1, 5], [2, 6], [3, 7]
];

let angleX = 0;
let angleY = 0;

function project([x, y, z]) {
    const size = Math.min(canvas.width, canvas.height) / 4;
    const perspective = 4 / (4 + z);
    const x2D = x * perspective * size + canvas.width / 2;
    const y2D = -y * perspective * size + canvas.height / 2;
    return [x2D, y2D];
}

function rotate([x, y, z], angleX, angleY) {
    const sinX = Math.sin(angleX);
    const cosX = Math.cos(angleX);
    const sinY = Math.sin(angleY);
    const cosY = Math.cos(angleY);

    let y1 = y * cosX - z * sinX;
    let z1 = y * sinX + z * cosX;

    let x1 = x * cosY + z1 * sinY;
    let z2 = -x * sinY + z1 * cosY;

    return [x1, y1, z2];
}

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    const rotatedVertices = vertices.map(vertex => rotate(vertex, angleX, angleY));
    const projectedVertices = rotatedVertices.map(project);

    ctx.strokeStyle = 'black';
    ctx.lineWidth = 2;

    edges.forEach(([start, end]) => {
        const [x1, y1] = projectedVertices[start];
        const [x2, y2] = projectedVertices[end];
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2);
        ctx.stroke();
    });

    angleX += 0.01;
    angleY += 0.01;

    requestAnimationFrame(draw);
}

draw();

window.addEventListener('resize', () => {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
});