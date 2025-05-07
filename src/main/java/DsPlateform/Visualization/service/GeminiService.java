package DsPlateform.Visualization.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey;

    // Your full visualization library (can also be loaded from a file if needed)
    private final String libraryContext = """
        You are an AI assistant designed to generate code strictly using the classes and methods from a specified JavaScript library for data structure visualization. The library documentation is hosted at the following link:

Library Documentation : https://zalaharshpalsinh.github.io/VisualDS/docs/enduser/vArray.html

Instructions:

1) Read and Understand Documentation:

2) Carefully read and analyze all the details on the provided documentation page, including any linked or referenced pages.

3) Extract the context of each class, its description, use cases, methods, their parameters, types, descriptions, and examples.

4) Retain all available details without making assumptions or hallucinating any missing information.

Code Generation Rules:

1) Use only the classes, methods, parameters, and examples explicitly mentioned in the documentation.

2) If the documentation does not explicitly mention something, respond that the required method or functionality is unavailable.

3) Strictly adhere to the library’s syntax and intended usage.

4) Only generate Javascript code.

Responding to Queries:

1) When given a user query, generate code that strictly adheres to the library’s functionality.

2) Use realistic, meaningful examples for method parameters and data values.

3) Clearly explain the generated code in comments (optional unless requested by the user).


Rules for interpretation of following context which MUST be followed:

1) Only interpret and use vElement(), vArray(), Pointer() classes and linearSearch(), binarySearch(), selectionSort(), bubbleSort(), insertionSort(), mergeSort() functions while answering, pretend everything else is not there(Completely ignore it)

2) Also within these classes ignore everything that is marked with @ignore annotation in jsDoc comments.

3) Use linearSearch(), binarySearch(), selectionSort(), bubbleSort(), insertionSort(), mergeSort() functions as a set of examples for reference. For example, if user asks the code for linear search, you are suppose to generate the code that is within the body of linear search function. Similarly study all functions mentioned in this rule, and use them to understand how library should be used. You should generate similar code for any other given problem, intelligently learning from the given examples of searching and sorting.

Here is the whole library's context:

/**
 * Represents a state. Every other state should extends this base class.
 */
declare class BaseState {
    /**
     * Executes tasks to be performed while entering this state
     * @param {object} enterParams object containing parameters required while entering this state
     */
    enter(enterParams: object): void;
    /**
     * Executes tasks to be performed while exiting this state
     */
    exit(): void;
    /**
     * Executes update logic of this state
     * @param {number} dt Delta time
     */
    update(dt: number): void;
    /**
     * Executes draw logic of this state
     * @param {CanvasRenderingContext2D} ctx The canvas context to draw with
     */
    draw(ctx: CanvasRenderingContext2D): void;
}

/**
 * A state manager to efficiently manage all the states of an entity and its transition between those states.
 */
declare class StateMachine {
    /**
     * @param {object} states An object whose keys are state names and values are functions which return new object of those states.
     * @param {string} intitialState Initial state of state machine
     */
    constructor(states: object, intitialState: string);
    /**
     * An object whose keys are state names and values are functions which return new object of those states.
     * @type {object}
     */
    states: object;
    /**
     * Object of current state of state machine
     * @type {BaseState}
     */
    currentState: BaseState;
    /**
     * Transitions the stateMachine from current state to a target state.
     * @param {string} toState State to transition to
     * @param {object} params Object storing all the parameters required while entering the target state
     */
    change(toState: string, params: object): void;
    /**
     * Performs the update logic of current state of the state machine. Should be called on every frame.
     * @param {number} dt Delta time
     */
    update(dt: number): void;
    /**
     * Performs the draw logic of current state of the state machine. Should be called on every frame.
     * @param {CanvasRenderingContext2D} ctx The 2D rendering context for the drawing surface of a <canvas> element.
     */
    draw(ctx: CanvasRenderingContext2D): void;
}

/**
 * Represent a single tween
 */
declare class Tween {
    /**
     *
     * @param {object} target The target object to tween
     * @param {object} startValues Values before tween
     * @param {object} changeValues Change in Values after tween
     * @param {number} startTime  Starting time of tween
     * @param {number} duration Duration of the tween in milliseconds
     * @param {function} easing Easing function
     * @param {function} callback Callback when tween completes
     */
    constructor(target: object, startValues: object, changeValues: object, startTime: number, duration: number, easing: Function, callback: Function);
    /**
     * The target object to tween
     * @type {object}
     */
    target: object;
    /**
     * Values before tween
     * @type {object}
     */
    startValues: object;
    /**
     * Change in Values after tween
     * @type {object}
     */
    changeValues: object;
    /**
     * Starting time of tween
     * @type {number}
     */
    startTime: number;
    /**
     * Duration of the tween in milliseconds
     * @type {number}
     */
    duration: number;
    /**
     * Easing function
     * @type {function}
     */
    easing: Function;
    /**
     * Callback when tween completes
     * @type {function}
     */
    callback: Function;
}

/**
 * A manager class which manages all tweens
 */
declare class TweenManager {
    animatorId: number;
    /**
     * A map of all unfinished tweens
     * @type {Map<number, Tween>}
     */
    tweens: Map<number, Tween>;
    /**
     * Id to be assigned to next tween
     * @type {number}
     */
    nextId: number;
    /**
     * Create a new tween
     * @param {object} target - The target object to tween
     * @param {object} values - The target values to tween to (e.g., {x: 100, y: 200})
     * @param {number} duration - Duration of the tween in milliseconds
     * @param {function} [easing] - Easing function (default: linear)
     * @param {function} [callback] - Callback when tween completes
     * @returns {number} - Tween ID
     */
    addTween(target: object, values: object, duration: number, easing?: Function, callback?: Function): number;
    /**
     * Cancel a tween
     * @param {number} id - The tween ID to cancel
     */
    cancel(id: number): void;
    /**
     * Cancel all tweens
     */
    cancelAll(): void;
    /**
     * Updates all active tweens
     * @param {number} dt Delta time
     */
    update(dt: number): void;
    /**
     * Check if a tween is active
     * @param {number} id - The tween ID to check
     * @returns {boolean} - True if the tween is active
     */
    isActive(id: number): boolean;
}
declare namespace TweenManager {
    function linear(t: any): any;
    function quadIn(t: any): number;
    function quadOut(t: any): number;
    function quadInOut(t: any): number;
    function cubicIn(t: any): number;
    function cubicOut(t: any): number;
    function cubicInOut(t: any): number;
    function sinIn(t: any): number;
    function sinOut(t: any): number;
    function sinInOut(t: any): number;
    function expoIn(t: any): number;
    function expoOut(t: any): number;
    function expoInOut(t: any): number;
}

/**
 * Draws a solid rectangle with provided specifications
 * @param {CanvasRenderingContext2D} ctx canvas context to draw on
 * @param {number} x X coordinate
 * @param {number} y Y coordinate
 * @param {number} width width of rectangle
 * @param {number} height height of rectangle
 * @param {string} fillColor color of rectangle
 * @param {string} borderColor color of border
 * @param {number} borderWidth thickness of border
 */
declare function drawRectangle(ctx: CanvasRenderingContext2D, x: number, y: number, width: number, height: number, fillColor: string, borderColor: string, borderWidth: number): void;
/**
 * Draws text with provided specifications
 * @param {CanvasRenderingContext2D} ctx canvas context to draw on
 * @param {string} text text to be drawn
 * @param {number} x X coordinate
 * @param {number} y Y coordinate
 * @param {string} font font to use
 * @param {string} color color of text
 * @param {CanvasTextAlign} alignment horizontal alignment
 * @param {CanvasTextBaseline} baseline vertical allignment
 */
declare function drawText(ctx: CanvasRenderingContext2D, text: string, x: number, y: number, font: string, color: string, alignment: CanvasTextAlign, baseline: CanvasTextBaseline): void;
/**
 * Draws a line with provided specifications
 * @param {CanvasRenderingContext2D} ctx canvas context to draw on
 * @param {*} sx starting x coordinate
 * @param {*} sy starting y coordinate
 * @param {*} ex ending x coordinate
 * @param {*} ey ending y coordinate
 * @param {*} color color of line
 * @param {*} width width of line
 */
declare function drawLine(ctx: CanvasRenderingContext2D, sx: any, sy: any, ex: any, ey: any, color: any, width: any): void;
/**
 * Gets the dimensions of a text with given font
 * @param {string} font font to be used while measuring dimensions
 * @param {string} text text to get dimensions for
 * @returns {{width: number, height: number}} dimensions of the text
 */
declare function getTextDimensions(font: string, text: string): {
    width: number;
    height: number;
};

/**
 * Represents an drawable entity which uses the framework functionalities and services of Animator to perform animations.
 */
declare class Entity {
    animatorId: number;
    /**
     * X coordinate assigned to entity by Animator
     * @type {number}
     */
    x: number;
    /**
     * Y coordinate assigned to entity by Animator
     * @type {number}
     */
    y: number;
    /**
     * Width of the entity in pixels
     * @type {number}
     */
    width: number;
    /**
     * Height of the entity in pixels
     * @type {number}
     */
    height: number;
    /**
     * Indicates whether this entity is removed or not
     *
     * true : this entity is removed from scope and will not be able to queue any animations
     * @type {boolean}
     */
    removed: boolean;
    /**
     * Set the coordinates of the top left corner of entity
     * @param {number} x The x coordinate
     * @param {number} y The y coordinate
     */
    setCoordinates(x: number, y: number): void;
    /**
     * Get the current coodinates of the entity
     * @returns {{x: number, y:number}} Object containing coordinates
     */
    getCoordinates(): {
        x: number;
        y: number;
    };
    /**
     * Register the entity object ( this ) to animator
     *
     * Immediate effect: Animator queues the Action of adding this entity
     *
     * Animation effect: Entity gets registered to animator and starts appearing on canvas
     */
    addInPool(): void;
    /**
     * Remove the entity object ( this ) from animator
     *
     * Immediate effect: Entity object is marked removed and will not be able to register any more animations and
     * animator queues the Action of removing this entity
     *
     * Animation effect: Entity gets removed from animator and disappears from the canvas.
     * Other entities move to fill the gap left by removed entity.
     */
    remove(): void;
    /**
     * Registers an animation to animator. Animator adds the animation in a queue
     *
     * Animator will notify the entity when this animation's turn come, by calling notify() method on entity.
     * And it will also pass the params object back to entity which is provided here, at the time of animation registration.
     *
     * Upon execution of queued animation, animator will move to and stay in 'animating' state until nextAnimation() is called on it.
     *
     * If entity is already removed, this call will have no effect.
     * @param {object} params object containing all extra parameters required to complete animation. This same object will be passed back to entity in notify() method
     */
    addAnimation(params: object): void;
    /**
     * Notifies the entity that its turn has arrived and it can perform the requested animation
     * @param {object} params object containing all extra parameters required to complete animation. This is the same object which was passed in while calling addAnimation.
     */
    notify(params: object): void;
    /**
     * Notifies the Animator about the completion of entity's animation, so that it can move to next animation.
     *
     * It must be called by the entity at the end of every animation, if it is not, next animations in the animator's queue will not execute.
     */
    nextAnimation(): void;
    /**
     * Gets the current animation speed of the animator
     * @returns {number} The current animation speed of the animator
     */
    getAnimationSpeed(): number;
    /**
     * Sets the animation speed of the animator
     * @param {number} newSpeed The new animation speed of the animator. It must be between 0.1 and 5.0
     */
    setAnimationSpeed(newSpeed: number): void;
    /**
     * Gets the tween manager of the animator. It can be used to create tweens for this entity.
     * @returns {TweenManager} The tween manager of the animator.
     */
    getTweenManager(): TweenManager;
    /**
     * Updates the entity. It is called on every frame if entity is registered to animator.
     * @param {number} dt Delta time
     */
    update(dt: number): void;
    /**
     * Draws the entity. It is called on every frame if entity is registered to animator.
     *
     * @param {CanvasRenderingContext2D} ctx The 2d context of canvas to draw with
     */
    draw(ctx: CanvasRenderingContext2D): void;
    /**
     * Performs the clean up tasks. It is called by the animator when the entity is removed from its pool.
     */
    cleanUp(): void;
}

/**
 * This is that class that encapsulates any object that is to be drawn.
 *
 * It may be number, string, or even a custom Student object, provided it has a toString() overridden
 * that returns the text representation of the object.
 *
 * User of the framework may want to create individual objects of this class that would be drawn (with isSlave = false)
 * and managed by the animator directly. This is useful when the user wants to show a single object on the screen.,
 * for example, a number or a string.
 *
 * or the user may not use this at all, instead the user may use higher level classes such as vArray
 * which internally uses vElement objects (with isSlave = true).
 */
declare class vElement extends Entity {
    /**
     * @param {*} val The object to visualize.In case it is a custom object it Must have a toString() overridden which returns a single or multi line
     * respresentation of object.
     * @param {string} label The label/name that is to be displayed when drawing this vElement. Defaults to empty string.
     * @param {boolean} isSlave True if this vElement is not to be managed by the animator directly
     * (managed by some master entity, such as vArray). Defaults to false.
     */
    constructor(val: any, label?: string, isSlave?: boolean);
    /**
     * The object encapsulated. This gets updated along with the synchronous code
     * (code written by the user).
     * @type {*}
     */
    val: any;
    /**
     * The object utilized to draw. This gets updated as and when the animation related to any updation is executed by animator.
     * @type {*}
     */
    drawVal: any;
    /**
     * The label/name that is to be displayed when drawing this vElement
     * @type {string}
     */
    label: string;
    /**
     * The color of the box in which to display the object
     * @type {string}
     */
    color: string;
    /**
     * The font style utilized to write the toString() text of the drawVal in the box
     * @type {string}
     */
    font: string;
    /**
     * The font style utilized to write the label
     * @type {string}
     */
    labelFont: string;
    /**
     * The text representation of the drawVal object, as an array of strings which are to be drawn on separate lines
     * @type {string[]}
     */
    text: string[];
    /**
     * The state machine to manage the states of vElement. Initial state is idle.
     * @type {StateMachine}
     */
    stateMachine: StateMachine;
    /**
     * Changes the state of the vElement object.
     * @param {string} toState The state to which to change
     * @param {object} enterParams An object containing the parameters required to enter the state and to be passed to the state's enter function.
     *
     * @ignore
     */
    changeState(toState: string, enterParams: object): void;
    /**
     * Calculates and sets the box's dimensions based on text and label (Dynamic adjustmenet of dimensions)
     * This is called when the text or label is changed, or when the vElement is created.
     * @ignore
     */
    syncDataAndVisual(): void;
    /**
     * Sets the new value of the encapsulated object
     * @param {*} val The new value
     * @param {boolean} highlight Is the vElement to be highlighted while showing the change on the screen
     *
     * @example <caption>Normal vanilla JS code</caption>
     * //Vanilla Js
     * let myVaribale = 10
     *
     * @example <caption>Corresponding code using vElement</caption>
     * //Using vElement
     * let myVariable = new VisualDS.vElement( 10, "myVariable" )
     * let x = new vElement(10)
     */
    setVal(val: any, highlight?: boolean): void;
    /**
     * Get the current value of the encapsulated object
     * @returns The current value stored by vElement
     */
    getVal(): any;
    /**
     * Hightlight the vElement with the given color.
     *
     * Registers the animation for changing the color of the box
     * @param {string} color The colour with which to highlight
     */
    highlight(color: string): void;
    /**
     * Unhightlights the vElement
     *
     * Registers the animation for changing the color of the box to default color
     */
    unhighlight(): void;
}

/**
 * This class represents a visual array.
 *
 * It can be an array of anything, i.e string, number, custom objects, etc.
 * The only restriction is that the class, whose objects are in the array, should have a toString() method
 * that returns what needs to be written in the visualized object in the visualized array.
 * It may be a single line text, or a multiline text. If just passing array of numbers, or strings, then no need to worry about toString
 * since it is there by default in these inbuilt classes.
 *
 * With its pushBack(), popBack(), pushFront(), popFront() methods, it can be used as a stack or queue as well.
 */
declare class vArray extends Entity {
    /**
     * @param {any[]} data The array data to be used for visualization
     * @param {string} label The label to draw above the array. Defaults to empty string.
     */
    constructor(data: any[], label?: string);
    /**
     * This is the actual data inside the array, that changes with the synchronous code
     * written by user.
     * @type {any[]}
     */
    data: any[];
    /**
     * The label to draw above the array
     * @type {string}
     */
    label: string;
    /**
     * This is the copy utilized for drawing on every frame.
     * It changes asynchronously, as and when animations related to
     * the varray object are selected from the animation queue.
     * @type {vElement[]}
     */
    drawData: vElement[];
    /**
     * The list of pointers (index variables) that point to elements of this array.
     *
     * Required, because along with the array itself, these also need to be drawn and updated on every frame.
     * @type {Pointer[]}
     */
    pointers: Pointer[];
    /**
     * Width of a single element drawn
     * @type {number}
     */
    boxWidth: number;
    /**
     * Height of a single element drawn
     * @type {number}
     */
    boxHeight: number;
    /**
     * To manage the animation via states
     * @type {StateMachine}
     */
    stateMachine: StateMachine;
    /**
     * Updates all the vElement objects encapsulated in this vArray
     * @param {number} dt The delta time
     *
     * @ignore
     */
    updateBoxes(dt: number): void;
    /**
     * Updates all the pointers encapsulated in this vArray
     * @param {number} dt The delta time
     *
     * @ignore
     */
    updatePointers(dt: number): void;
    /**
     * Draws the boxes for each element, basically just delegates to the draw of each vElement object in drawData
     * @param {CanvasRenderingContext2D} ctx The canvas context to draw on
     *
     * @ignore
     */
    drawBoxes(ctx: CanvasRenderingContext2D): void;
    /**
     * Draws all the pointers associated with this array
     * @param {CanvasRenderingContext2D} ctx The canvas context to draw on
     *
     * @ignore
     */
    drawPointers(ctx: CanvasRenderingContext2D): void;
    /**
     * Changes the state of the state machine to the given state
     * @param {string} toState The state to change to
     * @param {*} enterParams The object containing all the parameters required to enter the state
     *
     * @ignore
     */
    changeState(toState: string, enterParams: any): void;
    /**
     * Updates the boxWidth, boxHeight, Width, Height based on biggest vElement in drawData
     *
     * @ignore
     */
    syncDimensions(): void;
    /**
     * Updates the coordinates of each box, based on starting coordinates of array and boxWidth
     *
     * @ignore
     */
    syncCoordinates(): void;
    /**
     * Get the array length
     * @returns Length of the contained array
     */
    length(): number;
    /**
     * Get the value at a given index
     * @param {number} index The index to get value from
     * @returns The value at the given index
     */
    get(index: number): any;
    /**
     * Set a new value at a given index
     * @param {number} index The index for which to update value
     * @param {*} newVal The new value
     * @param {boolean} highlight Whether to highlight the element while showing updation on screen. Defaults to true.
     */
    set(index: number, newVal: any, highlight?: boolean): void;
    /**
     * Adds a new element at the end of array
     * @param {*} val The new value to add
     * @returns The new length of the array
     */
    pushBack(val: any): number;
    /**
     * Removes an element from the end of array
     * @returns The removed element. If array is empty, undefined is returned and array is not modified.
     */
    popBack(): any;
    /**
     * Adds a new element at the start of array
     * @param {*} val The new value to add
     * @returns The new length of the array
     */
    pushFront(val: any): number;
    /**
     * Removes an element from the start of array
     * @returns The removed element. If array is empty, undefined is returned and array is not modified.
     */
    popFront(): any;
    /**
     * Highlights a list of indices
     * @param {number[]} indices The list of indices
     * @param {string} color The color to highlight with. Defaults to blue.
     */
    highlight(indices: number[], color?: string): void;
    /**
     * Unhighlights a list of indices
     * @param {number[]} indices The list of indices
     */
    unhighlight(indices: number[]): void;
    /**
     * Highlights a range of indices, start and end are both inclusive.
     * @param {number} s The start of the range
     * @param {number} e The end of the range
     * @param {string} color The color to highlight with.
     */
    highlightRange(s: number, e: number, color: string): void;
    /**
     * Unhighlight a range of indices, start and end are both inclusive.
     * @param {number} s The start of the range
     * @param {number} e The end of the range
     */
    unhighlightRange(s: number, e: number): void;
    /**
     * Swaps elements at two indices
     * @param {number} i The first index
     * @param {number} j The second index
     * @param {boolean} highlight Whether to highlight the elements while showing animation. Defaults to true.
     */
    swap(i: number, j: number, highlight?: boolean): void;
    /**
    * Get a pointer(index variable) for this vArray instance.
    * @param {number} initIndex The initial index pointed by the pointer
    * @param {string} label The name to show for this pointer on screen.
    * @returns {Pointer}
    */
    getPointer(initIndex: number, label?: string): Pointer;
    /**
     * Remove a given pointer from this vArray instance.
     *
     * It has the following effect: It will not be drawn following this call, and you cannot queue any animations from the pointer
     * @param {Pointer} ptr The pointer to remove
     */
    removePointer(ptr: Pointer): void;
}

/**
 * This class represents a visual array index variable.
 *
 * DO NOT create an object of this using the constructor.
 * Always use getPointer() method of vArray to get an object of this.
 */
declare class Pointer extends Entity {
    /**
     * @param {vArray} pointee The vArray object this Pointer object will point to
     * @param {number} initialIndex The initial index
     * @param {string} label The label to show below this pointer. Defaults to empty string.
     */
    constructor(pointee: vArray, initialIndex: number, label?: string);
    /**
     * The vArray object this Pointer object will point to
     * @type {vArray}
     */
    pointee: vArray;
    /**
     * The index at which this pointer is pointing to. This is the index that changes when user makes changes.
     * @type {number}
     */
    index: number;
    /**
     * The index at which this pointer is drawn. This is the index that changes when animations are scheduled.
     * @type {number}
     */
    drawIndex: number;
    /**
     * The label to show below this pointer
     * @type {string}
     */
    label: string;
    /**
     * The state machine to handle the states of this pointer. Initial state is idle.
     * @type {StateMachine}
     */
    stateMachine: StateMachine;
    /**
     * Draws an arrow along with label and index
     * @param {CanvasRenderingContext2D} ctx The canvas context to draw on
     *
     * @ignore
     */
    drawArrow(ctx: CanvasRenderingContext2D): void;
    /**
     * Changes the state of the state machine to the given state
     * @param {string} toState The state to change to
     * @param {*} enterParams The object containing all the parameters required to enter the state
     *
     * @ignore
     */
    changeState(toState: string, enterParams: any): void;
    /**
     * Calculates the coordinates of pointer based on the vArray object coordinates and the index
     * @ignore
     */
    syncCoordinates(): void;
    /**
     * Get the current index value of the pointer
     * @returns current index value
     */
    getIndex(): number;
    /**
     * Check whether the current index value is out of the bounds of vArray
     * @returns {boolean} true if the index is out of bounds, false otherwise
     */
    isOutOfBound(): boolean;
    /**
     * Moves the pointer by the specified amount of change in index value.
     * @param {number} change The change to add in the index. Positive moves it ahead, negative moves it back.
     *
     * You can change the index however you want: no exception is thrown.
     * Instead, to handle such cases, the drawIndex value is capped till one before start and one after the end of array.
     */
    move(change: number): void;
    /**
     * Moves the pointer to a specified index
     * @param index The index to move to
     */
    moveTo(index: any): void;
    /**
     * Increment the index of pointer by 1
     *
     * Same as calling move(1)
     */
    increment(): void;
    /**
     * Decrement the index of pointer by 1
     *
     * Same as calling move(-1)
     */
    decrement(): void;
    /**
     * Hightlights the element where this pointer is pointing. No effect if isOutOfBound() returns true.
     * @param {string} color The color to highlight with
     */
    highlight(color?: string): void;
    /**
     * Unightlights the element where this pointer is pointing. No effect if isOutOfBound() returns true.
     */
    unhighlight(): void;
}

/**
 * Creates a new visualisation by creating a new animator object and executing the user given code.
 *
 * The user given code is expected to be a function which takes a controller object as an argument. The controller object contains methods to control the animation speed and other properties of the animator.
 *
 * Exposes the whole framework to the user.
 * @param {string} cnvId The ID of the canvas element in the DOM. This canvas will be used to draw on.
 * @param {function} userScript The callback containing code to be visualized.
 */
declare function createVisualisation(cnvId: string, userScript: Function): void;
/**
 * Gives the current animation speed.
 * @returns {number} current animation speed (A value multiplied with default speed)
 */
declare function getAnimationSpeed(animatorId: any): number;
/**
 * Sets a new animation speed for the specified animator. All animations after this call will execute at new speed.
 * @param {number} animatorId ID of the animator for which to set the animation speed.
 * @param {number} newSpeed New animation speed. A multiplier value for default speed. Default is 1.0 (1x).
 */
declare function setAnimationSpeed(animatorId: number, newSpeed: number): void;

/**
 * Creates a visualisation for performing a selection sort on the given array of numbers.
 * @param {number[]} inputArr the array of numbers to sort
 */
declare function selectionSort(inputArr: number[]): void;
/**
 * Creates a visualisation for performing a bubble sort on the given array of numbers.
 * @param {number[]} inputArr the array of numbers to sort
 */
declare function bubbleSort(inputArr: number[]): void;
/**
 * Creates a visualisation for performing an insertion sort on the given array of numbers.
 * @param {number[]} inputArr the array of numbers to sort
 */
declare function insertionSort(inputArr: number[]): void;
/**
 * Creates a visualisation for performing a merge sort on the given array of numbers.
 * @param {number[]} inputArr the array of numbers to sort
 */
declare function mergeSort(inputArr: number[]): void;

/**
 * Creates a visualisation for performing a linear search on the given array of numbers and highlights the first found element.
 * @param {number[]} inputArr the array of numbers to search in
 * @param {number} target the number to search for
 */
declare function linearSearch(inputArr: number[], target: number): void;
/**
 * Creates a visualisation for performing a binary search on the given sorted array of numbers and highlights the found element.
 * @param {number[]} sortedArr the sorted array of numbers to search in
 * @param {number} target the number to search for
 */
declare function binarySearch(sortedArr: number[], target: number): void;


Examples:

1) Query: Generate code for linear search visualisation
Response: 
// wrap the inpurArr in vArray along with the name of the algorithm as array's label
        let arr = new vArray( [1,2,3,4] , 'Linear Search' )

        // get the pointer to the first element of the array, labeled as 'I' and move it till the last element of the vArray linearly
        for ( let i = arr.getPointer( 0, 'I' ); !i.isOutOfBound() || i.remove(); i.increment() )
        {
                // highlight the current element and check if it is equal to the target
                i.highlight()
                if ( arr.get( i.getIndex() ) == target )
                {
                        // if it is, highlight it in green and remove the pointer
                        i.highlight( 'green' )
                        i.remove()
                        //search is over
                        break
                }
                else
                {
                        // if it is not, unhighlight the current element and move to the next one
                        i.unhighlight()
                }
        } 

2) Query: Generate code for binary search visualisation
Response:
// wrap the sortedArr in vArray along with the name of the algorithm as array's label
        let arr = new vArray( [1,2,3,4] , 'Binary Search' )

        // call the search function with the array, start index, end index and target number
        search( arr, 0, arr.length() - 1, target )


        // the search function is a recursive function that takes the array, start index, end index and target number as parameters
        function search( arr, s, e, target )
        {
                // base case: if the start index is greater than the end index, return
                if ( s > e ) return

                // highlight the range of elements being searched
                arr.highlightRange( s, e )

                // find the middle index of the current range and highlight it in red
                let mid = Math.floor( ( s + e ) / 2 )
                arr.highlight( [ mid ], 'red' )

                // unhighlight the range of elements being searched
                arr.unhighlightRange( s, e )

                // check if the middle element is greater than the target number
                if ( arr.get( mid ) > target )
                {
                        // if it is, call the search function recursively with the left half of the array
                        search( arr, s, mid - 1, target )
                }
                // check if the middle element is less than the target number
                else if ( arr.get( mid ) < target )
                {
                        // if it is, call the search function recursively with the right half of the array
                        search( arr, mid + 1, e, target )
                }
                // if the middle element is equal to the target number, highlight it in green and return
                else
                {
                        arr.highlight( [ mid ], 'green' )
                        return
                }
        }


3) Query: Generate code for selection sort visualisation
Response:
// wrap the inputArr in vArray along with the name of the algorithm as array's label
    let arr = new vArray( [1,2,3,4] , 'Selection Sort' )

    // get the pointer to the first element of the array, labeled as 'I' and move it till the last element of the vArray linearly
    for ( let i = arr.getPointer( 0, 'I' ); !i.isOutOfBound() || i.remove(); i.increment() )
    {
        // for each element, find the minimum element in the unsorted part of the array and swap it with the current element
        let min = i.getIndex()

        // highlight the current element and the minimum element
        i.highlight( 'green' )

        // loop through the rest of the array to find the minimum element
        // This other pointer is labeled as 'J' and moves from the current index to the end of the array
        for ( let j = arr.getPointer( i.getIndex() + 1, 'J' ); !j.isOutOfBound() || j.remove(); j.increment() )
        {
            // check if the current element is less than the minimum element
            if ( arr.get( j.getIndex() ) < arr.get( min ) )
            {
                // if it is, unhighlight the previous minimum element and highlight the current element as the new minimum element
                arr.unhighlight( [ min ] )
                min = j.getIndex()
                j.highlight( 'green' )
            }
        }

        //swap the current element with the minimum element
        arr.swap( i.getIndex(), min, false )
    }


4) Query: Generate code for bubble sort visualisation
Response:
// wrap the inputArr in vArray along with the name of the algorithm as array's label
    let arr = new vArray( [1,2,3,4] , 'Bubble Sort' )

    // get the pointer to the first element of the array, labeled as 'I' and move it till the last element of the vArray linearly
    for ( let i = arr.getPointer( 0, 'I' ); !i.isOutOfBound() || i.remove(); i.increment() )
    {
        let swap = false

        // for each iteration, loop through the unsorted part of the array and swap adjacent elements if they are in the wrong order
        // This other pointer is labeled as 'J' and moves from the first element to the last unsorted elemen
        for ( let j = arr.getPointer( 0, 'J' ); ( j.getIndex() < arr.length() - 1 - i.getIndex() ) || j.remove(); j.increment() )
        {
            // compare the current element with the next element and swap them if they are in the wrong order
            if ( arr.get( j.getIndex() ) > arr.get( j.getIndex() + 1 ) )
            {
                swap = true
                arr.swap( j.getIndex(), j.getIndex() + 1 )
            }
        }

        // highlight the element that was just sorted in this iteration
        arr.highlight( [ arr.length() - 1 - i.getIndex() ], 'green' )

        // if no swaps were made in this iteration, the array is already sorted, so we can break out of the loop
        if ( !swap )
        {
            // highlight the entire array in green to indicate that it is sorted
            let allIndex = []
            for ( let k = 0; k < arr.length(); k++ )allIndex.push( k )
            arr.highlight( allIndex, 'green' )
            i.remove()
            break
        }
    }

5) Query: Generate code for insertion sort visualisation
Response:
 // wrap the inputArr in vArray along with the name of the algorithm as array's label
    let arr = new vArray( [1,2,3,4] , 'Insertion Sort' )

    // highlight the first element in green to indicate that it is sorted
    arr.highlight( [ 0 ], 'green' )

    // get the pointer to the second element of the array, labeled as 'I' and move it till the last element of the vArray linearly
    for ( let i = arr.getPointer( 1, 'I' ); !i.isOutOfBound() || i.remove(); i.increment() )
    {
        // for each element, highlight it to indicate that it is being sorted
        i.highlight()

        // loop through the sorted part of the array to find the correct position for the current element
        // This other pointer is labeled as 'J' and moves from the current index to the beginning of the array
        let j = arr.getPointer( i.getIndex() - 1, 'J' )

        // check if the current element is greater than the element to its right
        while ( !j.isOutOfBound() && arr.get( j.getIndex() ) > arr.get( j.getIndex() + 1 ) )
        {
            // if it is, swap the two elements and move the pointer to the left
            arr.swap( j.getIndex(), j.getIndex() + 1, false )
            j.decrement()
        }

        // highlight the element that was just sorted in this iteration
        arr.highlight( [ j.getIndex() + 1 ], 'green' )
        j.remove()
    }

6) Query: Generate code for merge sort visualisation
Response:
// sort is a recursive function that takes the array, start index and end index as parameters and sorts the array using the merge sort algorithm
    function sort( arr, s, e )
    {
        // temporarily highlight the range of elements being sorted
        arr.highlightRange( s, e, 'SpringGreen' )
        arr.unhighlightRange( s, e )

        // base case: if the start index is equal to the end index, return
        if ( s === e ) return

        // find the middle index of the current range 
        let m = Math.floor( ( s + e ) / 2 )

        // call the sort function recursively with the left half of the array and the right half of the array
        sort( arr, s, m )
        sort( arr, m + 1, e )

        // merge the two halves of the array
        merge( arr, s, m, e )
    }

    // merge is a helper function that takes the array, start index, middle index and end index as parameters and merges the two halves of the array
    function merge( arr, s, m, e )
    {
        // highlight the range of elements being merged with different colors for the left and right halves
        arr.highlightRange( s, m, 'Turquoise' )
        arr.highlightRange( m + 1, e, 'Tomato' )

        // create two temporary arrays to hold the left and right halves of the array
        // add a '#' at the end of each array to indicate the end of the array (# is treated as infinity)
        let leftTmp = [], rightTmp = []
        for ( let i = s; i <= m; i++ )
        {
            leftTmp.push( arr.get( i ) )
        }
        leftTmp.push( '#' )
        for ( let i = m + 1; i <= e; i++ )
        {
            rightTmp.push( arr.get( i ) )
        }
        rightTmp.push( '#' )

        // wrap the left and right arrays in vArray along with proper names
        let left = new vArray( leftTmp, 'left' ), right = new vArray( rightTmp, 'right' )
        // highlight the entire left and right arrays in different colors
        left.highlightRange( 0, left.length() - 1, 'Turquoise' )
        right.highlightRange( 0, right.length() - 1, 'Tomato' )

        // get the pointer to the first element of the current sort range, labeled as 'I' and move it till the last element of the range linearly
        for ( let i = arr.getPointer( s, 'I' ); i.getIndex() <= e || i.remove(); i.increment() )
        {
            // compare the first elements of the left and right arrays
            if ( right.get( 0 ) === '#' || ( left.get( 0 ) !== '#' && left.get( 0 ) < right.get( 0 ) ) )
            {
                // if the left element is smaller, pop it from the left array and set it to the current index of the merged array
                arr.set( i.getIndex(), left.popFront() )
            }
            else
            {
                // if the right element is smaller, pop it from the right array and set it to the current index of the merged array
                arr.set( i.getIndex(), right.popFront() )
            }
        }

        // highlight the merged range in green to indicate that it is sorted
        arr.highlightRange( s, e, 'green' )

        // remove the left and right arrays from the visualisation
        left.remove()
        right.remove()
    }

    // wrap the inputArr in vArray along with the name of the algorithm as array's label
    let arr = new vArray( [1,2,3,4] , 'Merge Sort' )
    // call the sort function with the array, start index and end index
    sort( arr, 0, arr.length() - 1 )
    """;

    public GeminiService(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String generateAlgorithmCode(String algorithmType) {
        try {
            // Combine library + algorithm-specific instruction
            String fullPrompt = libraryContext + "\n\nNow generate visualization code for the " + algorithmType + " algorithm.";

            // Build Gemini request body
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> content = new HashMap<>();
            List<Map<String, String>> parts = new ArrayList<>();

            Map<String, String> textPart = new HashMap<>();
            textPart.put("text", fullPrompt);
            parts.add(textPart);
            content.put("parts", parts);

            List<Map<String, Object>> contents = new ArrayList<>();
            contents.add(content);
            requestBody.put("contents", contents);

            Map<String, Object> generationConfig = new HashMap<>();
            generationConfig.put("temperature", 0.7);
            generationConfig.put("maxOutputTokens", 4096);
            requestBody.put("generationConfig", generationConfig);

            // Call Gemini API
            Map<String, Object> response = webClient.post()
                    .uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response != null && response.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> candidateContent = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> candidateParts = (List<Map<String, Object>>) candidateContent.get("parts");

                    if (!candidateParts.isEmpty()) {
                        String text = (String) candidateParts.get(0).get("text");
                        return extractCodeBlock(text);
                    }
                }
            }

            return "Failed to generate algorithm code";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private String extractCodeBlock(String text) {
        int startJs = text.indexOf("```javascript");
        if (startJs != -1) {
            int endJs = text.indexOf("```", startJs + 12);
            if (endJs != -1) {
                return text.substring(startJs + 13, endJs).trim();
            }
        }

        int start = text.indexOf("```");
        if (start != -1) {
            int end = text.indexOf("```", start + 3);
            if (end != -1) {
                return text.substring(start + 3, end).trim();
            }
        }

        return text;
    }
}

