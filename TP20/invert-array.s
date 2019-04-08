	.equ SYS_EXIT, 1
	.equ SYS_READ, 3
	.equ SYS_WRITE, 4

	.arm
	.data

sz:	.word 10
ary:	.word 30,52,3,47,522,6,17,10,8,44

pad:	.space 256
	
	.text
	.globl _start

inverse:
	push {,lr}
	ldr r2,[r0]
	mov r3,#0
	
loop:	cmp r3,r1
	bge end
	add r3,r3,#1
	ldr r4,[r2],#4
	cmp r5,r2
	moveq r3,r4
	beq end
	bne loop
 
end:	mov r0,r3
	pop {,pc}

_start:	ldr r0, =ary
	ldr r2, =sz
	ldr r1,[r2]
	bl inverse
	
	mov r1,r0
	ldr r0, =pad
	bl sprintfd
	mov r2, r1
	mov r1, r0
	mov r0, #1
	
	mov r7, #SYS_WRITE
	swi #0

	mov r7,#SYS_EXIT
	mov r0,#0
	swi #0x0
	
	.end