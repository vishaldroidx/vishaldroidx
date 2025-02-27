// Get the display element
const display = document.getElementById('display');

// Function to append values to the display
function appendToDisplay(value) {
    // Check if we're starting a new calculation after a result
    if (display.value === 'Error' || display.value === 'Infinity') {
        display.value = '';
    }
    
    // Handle cases where we don't want consecutive operators
    const lastChar = display.value.slice(-1);
    if (['+', '-', '*', '/'].includes(lastChar) && ['+', '*', '/'].includes(value)) {
        // Replace the last operator with the new one
        display.value = display.value.slice(0, -1) + value;
        return;
    }
    
    // Handle decimal point - prevent multiple decimal points in the same number
    if (value === '.') {
        // Split by operators to get the current number being entered
        const parts = display.value.split(/[\+\-\*\/]/);
        const currentNumber = parts[parts.length - 1];
        
        // If the current number already has a decimal point, don't add another
        if (currentNumber.includes('.')) {
            return;
        }
    }
    
    display.value += value;
}

// Function to clear the display
function clearDisplay() {
    display.value = '';
}

// Function to calculate the result
function calculate() {
    try {
        // Check if the expression ends with an operator
        const lastChar = display.value.slice(-1);
        if (['+', '-', '*', '/'].includes(lastChar)) {
            display.value = display.value.slice(0, -1);
        }
        
        // Evaluate the expression
        const result = eval(display.value);
        
        // Check if the result is a valid number
        if (isNaN(result) || !isFinite(result)) {
            display.value = 'Error';
        } else {
            // Format the result to avoid extremely long decimal numbers
            display.value = parseFloat(result.toFixed(10)).toString();
        }
    } catch (error) {
        display.value = 'Error';
    }
}

// Function to handle backspace
function backspace() {
    if (display.value === 'Error' || display.value === 'Infinity') {
        display.value = '';
    } else {
        display.value = display.value.slice(0, -1);
    }
}

// Add keyboard support
document.addEventListener('keydown', function(event) {
    const key = event.key;
    
    // Numbers and operators
    if (/^[0-9]$/.test(key)) {
        appendToDisplay(key);
    } else if (['+', '-', '*', '/'].includes(key)) {
        appendToDisplay(key);
    } else if (key === '.') {
        appendToDisplay('.');
    } else if (key === 'Enter' || key === '=') {
        calculate();
    } else if (key === 'Escape' || key === 'c' || key === 'C') {
        clearDisplay();
    } else if (key === 'Backspace') {
        backspace();
    }
});