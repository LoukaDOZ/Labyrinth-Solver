	.equ SYS_EXIT, 1
	.equ SYS_READ, 3
	.equ SYS_WRITE, 4

	.arm
	.data

sz:	.word 10
ary:	.word 10,52,3,47,522,6,17,10,8,44
find:	.word 522

pad:	.space 256
	
	.text
	.globl _start

memb:	push {r3,r4,r5,lr}
	ldr r5,[r0]
	mov r4,#0
	mov r3,#-1
	
loop:	cmp r4,r1
	bge end
	add r4,r4,#1
	ldr r5,[r0],#4
	cmp r5,r2
	moveq r3,r4
	beq end
	bne loop
 
end:	mov r0,r3
	pop {r3,r4,r5,pc}

_start:	ldr r0, =ary
	ldr r2, =sz
	ldr r1,[r2]
	ldr r3, =find
	ldr r2,[r3]
	bl memb
	
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
	